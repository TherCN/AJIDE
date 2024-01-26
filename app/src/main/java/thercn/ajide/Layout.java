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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import thercn.ajide.R;
import thercn.ajide.adapter.FileAdapter;
import thercn.ajide.adapter.FileEditAdapter;
import thercn.ajide.utils.APPUtils;
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

	public void addFileTab(String file) {
		SharedPreferences.Editor speditor = sharedPreferences.edit();
		if (!sharedPreferences.getString("files", "").contains(file)) {
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
		IDECodeEditor editor = new IDECodeEditor(activity);
		editor.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		try {
			editor.setFile(file);
		} catch (IOException e) {}
		adapter.addView(editor);

	}

	public void removeFileTab(String file) {

		SharedPreferences.Editor speditor = sharedPreferences.edit();
		//Log.e("关闭", file);
		//Log.e("当前", getCodeEditor().getCurrentFile());
		if (getCodeEditor().getCurrentFile().equals(file)) {
			Log.e("", "关闭" + file);
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
		Log.e("关闭", file);
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

		PopupMenu popupMenu = new PopupMenu(activity, t.view);
		popupMenu.inflate(R.menu.file_menu);

		popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
				@Override
				public boolean onMenuItemClick(MenuItem item) {
					switch (item.getItemId()) {
						case R.id.closeCurrent:
							removeFileTab(getCodeEditor().getCurrentFile());
							break;
						case R.id.closeAll:
							for (int i = 0; i < openedFiles.size(); i++) {
								removeFileTab(i);
							}
							fileTabs.removeAllTabs();
							checkTabs();
							viewPager.removeAllViews();
							openedFiles.clear();
							break;
						case R.id.closeOther:
							for (int i = 0; i < openedFiles.size(); i++) {
								if (!openedFiles.get(i).equals(getCodeEditor().getCurrentFile())) {
									removeFileTab(i);
									openedFiles.remove(i);
									i--;
								}
							}
					}

					return true;
				}

			});
		popupMenu.show();
	}

}
