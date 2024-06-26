package thercn.ajide;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RadioGroup.LayoutParams;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
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
import io.github.rosemoe.sora.widget.SymbolInputView;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import io.github.rosemoe.sora.widget.schemes.SchemeVS2019;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import thercn.ajide.R;
import thercn.ajide.activities.IDEActivity;
import thercn.ajide.adapter.FileAdapter;
import thercn.ajide.adapter.FileEditAdapter;
import thercn.ajide.project.ProjectUtils;
import thercn.ajide.project.compiler.JCCompiler;
import thercn.ajide.utils.APPUtils;
import thercn.ajide.utils.Permission;
import thercn.ajide.utils.TLog;
import thercn.ajide.views.IDECodeEditor;

public class IDEActivityLayout {

	static IDEActivity activity;
	String projectPath;
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
	ActionBarDrawerToggle toggle;
	SymbolInputView siv;
	boolean isInitDone;
	boolean fileManagerInited;

    public IDEActivityLayout(IDEActivity activity) {
		this.activity = activity;
		init();
	}

	public void init() {
		if (isInitDone) {
			return;
		}
		toolbar = activity.findViewById(R.id.toolbar);
		toolbar.setTitle(R.string.app_name);

		activity.setSupportActionBar(toolbar);
		abar = activity.getSupportActionBar();
		viewPager = activity.findViewById(R.id.view_pager);

		fileList = activity.findViewById(R.id.filelist1);

		drawerLayout = activity.findViewById(R.id.drawerlayout);
        toggle = new ActionBarDrawerToggle(activity, drawerLayout, toolbar,
										   R.string.navigation_drawer_open, 
										   R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

		siv = activity.findViewById(R.id.siv);
		String[] charArray = new String[] {
			"→",
			"{",
			"}",
			"(",
			")",
			";",
			",",
			".",
			"=",
			"\"",
			"|",
			"&",
			"!",
			"[",
			"]",
			"<",
			"%",
			">",
			"+",
			"-",
			"/",
			"*",
			"?",
			":",
			"_"};
		String[] insertCharArray = new String[] {
			"\t",
			"{",
			"}",
			"(",
			")",
			";",
			",",
			".",
			"=",
			"\"",
			"|",
			"&",
			"!",
			"[",
			"]",
			"<",
			"%",
			">",
			"+",
			"-",
			"/",
			"*",
			"?",
			":",
			"_"};
		siv.addSymbols(charArray, insertCharArray);
		siv.setVisibility(View.GONE);

		fileTabs = activity.findViewById(R.id.tabs);
		fileTabs.setVisibility(View.GONE);

		adapter = new FileEditAdapter();
		viewPager.setAdapter(adapter);

		fileNotOpened = activity.findViewById(R.id.noOpenFile);
		sharedPreferences = activity.getSharedPreferences("openedFiles", Context.MODE_PRIVATE);
		String files = sharedPreferences.getString("files", "");
		if (!sharedPreferences.getString("files", "").isEmpty()) {
			for (int i = 0; i < files.split(";").length; i++) {
				addFileTab(files.split(";")[i]);
				Log.e("添加", files.split(";")[i]);
			}
		}
		isInitDone = true;
		Log.e("", getAllCodeEditorView().size() + "");
		if (getAllCodeEditorView().size() >= 1) {
			siv.bindEditor(getAllCodeEditorView().get(0));
			Log.e("", "已将符号输入视图挂载到" + getAllCodeEditorView().get(0));
		}
		fileTabs.setupWithViewPager(viewPager);
		fileTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
				@Override
				public void onTabSelected(TabLayout.Tab t) {
					if (isInitDone && getAllCodeEditorView().size() != 0) {
						siv.bindEditor(getAllCodeEditorView().get(t.getPosition()));
					}
				};
				@Override
				public void onTabUnselected(TabLayout.Tab t) {};
				@Override
				public void onTabReselected(TabLayout.Tab t) {
					selectTab(t);
				}
			});
		inflateFileList(ProjectUtils.getProjectPath());
	}

	public DrawerLayout getDrawerLayout() {
		return drawerLayout;
	}

	public List<IDECodeEditor> getAllCodeEditorView() {
		return adapter.getAllFileEditor();
	}

	public void saveAllFiles() {
		List<IDECodeEditor> editors = getAllCodeEditorView();
		for (IDECodeEditor i : editors) {
			try {
				i.saveFile();
			} catch (IOException e) {}
		}
		if (!editors.isEmpty()) {
			onSave();
		}
	}

	public void inflateFileList(String path) {
		List<File> newFiles = new ArrayList<File>();
		if (Permission.isPermissionGranted(activity)) {
			newFiles.add(new File(path).getParentFile());
			File[] files = APPUtils.getFiles(path);
			for (int i = 0; i < files.length; i++) {
				newFiles.add(files[i]);
			}
		} else {
			newFiles.add(new File("/"));
		}
		FileAdapter<File> adapter = new FileAdapter<File>(activity, newFiles, fileList);
		fileList.setAdapter(adapter);
		fileList.setLayoutManager(new LinearLayoutManager(activity));
	}

	public void refershFileList() {
		FileAdapter<File> fileAdapter = ((FileAdapter<File>)fileList.getAdapter());
		fileAdapter.setCurrentDir(fileAdapter.getCurrentDir().getAbsolutePath());
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

	public String getCurrentFile() {
		return openedFiles.get(viewPager.getCurrentItem());
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
			siv.setVisibility(View.VISIBLE);
		}

		fileTabs.addTab(fileTabs.newTab());
		openedFiles.add(file);
		final IDECodeEditor editor = new IDECodeEditor(activity);
		editor.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		try {
            editor.setColorScheme(activity.isDarkMode() ? new EditorColorScheme() : new SchemeVS2019());
			editor.setFile(file);
			editor.setTypefaceText(Typeface.createFromFile("/system/fonts/DroidSansMono.ttf"));
			adapter.addView(editor);
			drawerLayout.close();
			final DiagnosticsContainer con = new DiagnosticsContainer();
			final ArrayList<String> list = new ArrayList<>();
			//list.add("-sourcepath");
			//list.add("/sdcard/AJIDE/ClassPath/android.jar");
			list.add("-bootclasspath");
			list.add("/sdcard/AJIDE/ClassPath/android.jar:/sdcard/AJIDE/ClassPath/core-lambda-stubs.jar");
			list.add("-Xlint:all");
			list.add("-d");
			list.add("/sdcard/AJIDE");
			if (file.endsWith(".java")) {
				editor.setEditorLanguage(new JavaLanguage());
				if (editor.getCurrentFile().contains(ProjectUtils.getProjectPath())) {
					editor.setDiagnostics(con);
					compile(con, list);
				}
			}
			editor.subscribeEvent(
				ContentChangeEvent.class,
				new EventReceiver<ContentChangeEvent>() {
					long lastInvoke = System.currentTimeMillis();
					public void onReceive(ContentChangeEvent event, Unsubscribe unsubscribe) {
						if (!getCodeEditor().getTitle().equals(APPUtils.getFileName(getCodeEditor().getCurrentFile()) + "*")) {
							getCodeEditor().setTitle(APPUtils.getFileName(getCodeEditor().getCurrentFile()) + "*");
							adapter.notifyDataSetChanged();
							getCodeEditor().requestFocus();
						}
						if (file.endsWith(".java") && editor.getCurrentFile().contains(ProjectUtils.getProjectPath())) {
							long curInvoke = System.currentTimeMillis();
							if (curInvoke - lastInvoke > 500) {
								compile(con, list);
							}
							lastInvoke = System.currentTimeMillis();
						}
					}
				});

		} catch (IOException e) {
			TLog.e(e);
		}
	}

	public void onSave() {
		adapter.notifyDataSetChanged();
		if (getCodeEditor() != null) {
			getCodeEditor().requestFocus();
		}
	}

	public void compile(final DiagnosticsContainer con, List<String> args) {
		con.reset();
		try {
			final JCCompiler th = new JCCompiler(args)
				.addSourceFromFiles(APPUtils.getAllFile(ProjectUtils.getProjectPath(), ".java"));
			th.setJavaVersion(8);
			Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						for (Diagnostic<? extends JavaFileObject> i : th.compile()) {
							if (i.getSource() != null) {
								if (!i.getSource().getName().equals("/" + APPUtils.getFileName(getCurrentFile()))) {
									continue;
								}
							}
							short informationType = 0;
							Diagnostic.Kind kind = i.getKind();
							if (kind == Diagnostic.Kind.WARNING) {
								informationType = DiagnosticRegion.SEVERITY_WARNING;
							} else if (kind == Diagnostic.Kind.ERROR) {
								informationType = DiagnosticRegion.SEVERITY_ERROR;
							} else {
								informationType = DiagnosticRegion.SEVERITY_TYPO;
							}

							final DiagnosticRegion diag = new DiagnosticRegion((int)i.getStartPosition(), (int)i.getEndPosition(),
																			   informationType,
																			   0,
																			   new DiagnosticDetail("", i.getMessage(Locale.getDefault()), null, null));
							con.addDiagnostic(diag);
						}
						if (th.getOutput().isEmpty()) con.reset();
					}
				});
			thread.start();

		} catch (IOException e) {}
	}

	public class CompilationCallable implements Callable<Boolean> {

		// 假设 th 和 con 是通过构造函数传递的或者已经设置为类字段
		private final JCCompiler th;
		private final DiagnosticsContainer con;

		public CompilationCallable(JCCompiler th, DiagnosticsContainer con) {
			this.th = th;
			this.con = con;
		}

		@Override
		public Boolean call() {
			boolean compilationSuccess = true;
			for (Diagnostic<? extends JavaFileObject> diagnostic : th.compile()) {
				if (diagnostic.getSource() != null) {
					if (!diagnostic.getSource().getName().equals("/" + APPUtils.getFileName(getCurrentFile()))) {
						continue;
					}
				}
				short informationType = 0;
				Diagnostic.Kind kind = diagnostic.getKind();
				if (kind == Diagnostic.Kind.WARNING) {
					informationType = DiagnosticRegion.SEVERITY_WARNING;
				} else if (kind == Diagnostic.Kind.ERROR) {
					informationType = DiagnosticRegion.SEVERITY_ERROR;
					compilationSuccess = false;
				} else {
					informationType = DiagnosticRegion.SEVERITY_TYPO;
				}

				DiagnosticRegion diag = new DiagnosticRegion((int) diagnostic.getStartPosition(), (int) diagnostic.getEndPosition(),
															 informationType, 0,
															 new DiagnosticDetail("", diagnostic.getMessage(Locale.getDefault()), null, null));
				con.addDiagnostic(diag);
			}
			if (th.getOutput().isEmpty()) {
				con.reset();
			}
			return compilationSuccess;
		}

		// 假设 getCurrentFile() 是一个类方法，返回当前文件的对象
		
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
			siv.setVisibility(View.GONE);
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
						try {
							getCodeEditor().saveFile();
						} catch (IOException e) {}
						removeFileTab(getCodeEditor().getCurrentFile());
					} else if (item.getItemId() == R.id.closeAll) {
						saveAllFiles();
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
									try {
										getAllCodeEditorView().get(j).saveFile();
									} catch (IOException e) {}
									removeFileTab(j);
									openedFiles.remove(j);
									j--; // 调整j的值以反映从列表中删除的元素
								}
							}
						}
						onSave();
					}
					return true;
				}
			});
		popupMenu.show();
	}

}
