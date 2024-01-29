package thercn.ajide;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup.LayoutParams;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import io.github.rosemoe.sora.event.ContentChangeEvent;
import io.github.rosemoe.sora.event.EventReceiver;
import io.github.rosemoe.sora.event.Unsubscribe;
import io.github.rosemoe.sora.lang.diagnostic.DiagnosticDetail;
import io.github.rosemoe.sora.lang.diagnostic.DiagnosticRegion;
import io.github.rosemoe.sora.lang.diagnostic.DiagnosticsContainer;
import io.github.rosemoe.sora.langs.java.JavaLanguage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import thercn.ajide.R;
import thercn.ajide.activities.IDEActivity;
import thercn.ajide.adapter.FileAdapter;
import thercn.ajide.adapter.FileEditAdapter;
import thercn.ajide.project.AndroidProject;
import thercn.ajide.project.compiler.JCCompiler;
import thercn.ajide.utils.APPUtils;
import thercn.ajide.utils.TLog;
import thercn.ajide.views.IDECodeEditor;

public class Layout {

	static IDEActivity activity;
	Toolbar toolbar;
	IDECodeEditor textEditor;
	DrawerLayout drawerLayout;
	RecyclerView fileList;
	TabLayout fileTabs;
	ViewPager viewPager;
	FileEditAdapter adapter;
	LinearLayout fileNotOpened;
	List<String> openedFiles = new ArrayList<>();
	FloatingActionButton fab;
	SharedPreferences sharedPreferences;
	ActionBar abar;
	List<View> widgets;
	boolean isInitDone;

    public Layout(IDEActivity activity) {
		this.activity = activity;
		widgets = new ArrayList<>();
	}

	public void init() {

		toolbar = activity.findViewById(R.id.toolbar);
		toolbar.setTitle(R.string.app_name);
		widgets.add(toolbar);
		activity.setSupportActionBar(toolbar);
		abar = activity.getSupportActionBar();
		viewPager = activity.findViewById(R.id.view_pager);

		widgets.add(viewPager);

		drawerLayout = activity.findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, drawerLayout, toolbar,															 R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
		fileTabs = activity.findViewById(R.id.tabs);
		fileList = activity.findViewById(R.id.filelist1);
		inflateFileList("/sdcard");
		fileTabs.setVisibility(View.GONE);

		widgets.add(drawerLayout);
		widgets.add(fileList);

		adapter = new FileEditAdapter();
		viewPager.setAdapter(adapter);

		fileNotOpened = activity.findViewById(R.id.noOpenFile);
		fab = activity.findViewById(R.id.errors);
		fab.setVisibility(View.GONE);
		sharedPreferences = activity.getSharedPreferences("openedFiles", Context.MODE_PRIVATE);
		String files = sharedPreferences.getString("files", "");
		if (!sharedPreferences.getString("files", "").isEmpty()) {
			for (int i = 0; i < files.split(";").length; i++) {
				addFileTab(files.split(";")[i]);
				Log.e("添加", files.split(";")[i]);
			}
			isInitDone = true;
		}

		fileTabs.setupWithViewPager(viewPager);
		fileTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
				@Override
				public void onTabSelected(TabLayout.Tab t) {

				}

				@Override
				public void onTabUnselected(TabLayout.Tab t) {

				}

				@Override
				public void onTabReselected(TabLayout.Tab t) {
					selectTab(t);
				}
			});


		//headerList = activity.findViewById(R.id.headerList);

		new Handler(Looper.getMainLooper()).postDelayed(new Runnable(){

				@Override
				public void run() {
					if (openedFiles.size() > 0) {
						activity.enableMenu();
					}
				}
			}, 1000);
	}

	public void inflateFileList(String path) {
		List<File> newFiles = new ArrayList<File>();
		newFiles.add(new File(path).getParentFile());
		File[] files = APPUtils.getFiles(path);
		for (int i = 0; i < files.length; i++) {
			newFiles.add(files[i]);
		}
		FileAdapter<File> adapter = new FileAdapter<File>(activity, newFiles, fileList);
		fileList.setAdapter(adapter);
		fileList.setLayoutManager(new LinearLayoutManager(activity));
		if (adapter.getCurrentDir().isDirectory()) {
			new AndroidProject(adapter.getCurrentDir().getAbsolutePath()).setupGradle(null);
		}
	}

	public IDECodeEditor getCodeEditor() {
		if (viewPager.getChildCount() > 0) {
			return adapter.getCurrentEditor(viewPager.getCurrentItem());
		}
		return null;
	}

	public ActionBar getActionBar() {
		return activity.getSupportActionBar();
	}

	public List<String> getOpenedFiles() {
		return openedFiles;
	}

	public List<View> getAllWidgets() {
		return widgets;
	}

	public static IDEActivity getMainActivity() {
		return activity;
	}

	public void addFileTab(final String file) {

		if (!new File(file).exists()) {
			SharedPreferences.Editor speditor = sharedPreferences.edit();
			if (sharedPreferences.getString("files", "").contains(file)) {
				speditor.putString("files", sharedPreferences.getString("files", "").replace(file + ";", ""));
				speditor.commit();
				speditor.apply();
			}
			return;
		}
		SharedPreferences.Editor speditor = sharedPreferences.edit();
		if (!sharedPreferences.getString("files", "").contains(file + ";")) {
			speditor.putString("files", file + ";" + sharedPreferences.getString("files", ""));
			speditor.commit();
			speditor.apply();
		} else {
			if (isInitDone) {
				return;
			}
		}
		if (fileNotOpened.getVisibility() == View.VISIBLE) {
			fileNotOpened.setVisibility(View.GONE);
			fileTabs.setVisibility(View.VISIBLE);
		}
		if (activity.actionBarMenu != null) {
			activity.enableMenu();
		}
		fileTabs.addTab(fileTabs.newTab());
		openedFiles.add(file);
		final IDECodeEditor editor = new IDECodeEditor(activity);

		final DiagnosticsContainer con = new DiagnosticsContainer();

		final ArrayList<String> list = new ArrayList<>();
		
		list.add("-bootclasspath");
		list.add("/sdcard/AJIDE/ClassPath/android.jar:/sdcard/AJIDE/ClassPath/core-lambda-stubs.jar");
		list.add("-Xlint:all");
		list.add("-d");
		list.add("/sdcard/AJIDE");
		editor.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		try {
			editor.setFile(file);
			if (file.endsWith(".java")) {
				editor.setEditorLanguage(new JavaLanguage());
				editor.setDiagnostics(con);
				compile(con,list,editor);
				editor.subscribeEvent(
					ContentChangeEvent.class,
					new EventReceiver<ContentChangeEvent>() {
						long lastInvoke = System.currentTimeMillis();
						public void onReceive(ContentChangeEvent event, Unsubscribe unsubscribe) {
							long curInvoke = System.currentTimeMillis();
							if (curInvoke - lastInvoke > 100) {
								compile(con,list,editor);
							}
							lastInvoke = System.currentTimeMillis();
						}
					});
			}
		} catch (IOException e) {
			TLog.e(e);
		}
		adapter.addView(editor);

	}

	public void compile(DiagnosticsContainer con, List<String> args, IDECodeEditor editor) {
		con.reset();
		JCCompiler th = new JCCompiler(args)
			.addSource(APPUtils.getFileName(editor
						   .getCurrentFile())
					   .replace(".java", ""), editor
					   .getText()
					   .toString());
		th.setJavaVersion(8);
		th.start();
		for (Diagnostic<? extends JavaFileObject> i : th.getOutput()) {
			short informationType = 0;
			Diagnostic.Kind kind = i.getKind();
			if (kind == Diagnostic.Kind.WARNING) {
				informationType = DiagnosticRegion.SEVERITY_WARNING;
			} else if (kind == Diagnostic.Kind.ERROR) {
				informationType = DiagnosticRegion.SEVERITY_ERROR;
			} else {
				informationType = DiagnosticRegion.SEVERITY_TYPO;
			}

			DiagnosticRegion diag = new DiagnosticRegion((int)i.getStartPosition(), (int)i.getEndPosition(),
														 informationType,
														 0,
														 new DiagnosticDetail("语法错误", i.getMessage(Locale.getDefault()), null, null));
			con.addDiagnostic(diag);											 
		}
	}

	public void removeFileTab(String file) {

		SharedPreferences.Editor speditor = sharedPreferences.edit();
		Log.e("关闭", file);
		//Log.e("当前", getCodeEditor().getCurrentFile());
		if (getCodeEditor().getCurrentFile().equals(file)) {

			if (sharedPreferences.getString("files", "").contains(file)) {
				speditor.putString("files", sharedPreferences.getString("files", "").replace(file + ";", ""));
				speditor.commit();
				speditor.apply();
			}
			adapter.destroyItem(viewPager, viewPager.getCurrentItem(), adapter.getCurrentEditor(viewPager.getCurrentItem()));
			adapter.removeView(viewPager.getCurrentItem());
			checkTabs();
		}

	}

	public void removeFileTab(int index) {

		SharedPreferences.Editor speditor = sharedPreferences.edit();
		String file = openedFiles.get(index);
		if (adapter.getCurrentEditor(index).getCurrentFile().equals(file)) {
			Log.e("", "关闭" + file);
			if (sharedPreferences.getString("files", "").contains(file)) {
				speditor.putString("files", sharedPreferences.getString("files", "").replace(file + ";", ""));
				speditor.commit();
				speditor.apply();
			}
			adapter.destroyItem(viewPager, index, adapter.getCurrentEditor(index));
			adapter.removeView(index);
		}

	}

	public void checkTabs() {
		if (fileTabs.getTabCount() == 0) {
			fileTabs.setVisibility(View.GONE);
			fileNotOpened.setVisibility(View.VISIBLE);
		}
	}

	public void selectTab(TabLayout.Tab t) {
		Log.e("", "onTabSelected");
		Log.e("当前文件", getCodeEditor().getCurrentFile());
		PopupMenu popupMenu = new PopupMenu(activity, t.view);
		popupMenu.inflate(R.menu.file_menu);

		popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
				@Override
				public boolean onMenuItemClick(MenuItem item) {
					if (item.getItemId() == R.id.closeCurrent) {
						removeFileTab(getCodeEditor().getCurrentFile());
					} else if (item.getItemId() == R.id.closeAll) {
						for (int i = 0; i < openedFiles.size(); i++) {
							removeFileTab(i);
							openedFiles.remove(i);
							i--;
						}
						fileTabs.removeAllTabs();
						checkTabs();
						viewPager.removeAllViews();
					} else if (item.getItemId() == R.id.closeOther) {
						String currentFile = getCodeEditor().getCurrentFile();
						for (int i = 0; i < openedFiles.size(); i++) {
							for (int j = 0; j < openedFiles.size(); j++) {
								if (!openedFiles.get(j).equals(currentFile)) {
									removeFileTab(j);
									openedFiles.remove(j);
									j--; // 调整j的值以反映从列表中删除的元素
								}
							}
						}
					}
					

					return true;
				}

			});
		popupMenu.show();
	}

}
