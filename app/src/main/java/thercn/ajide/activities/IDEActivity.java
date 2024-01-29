package thercn.ajide.activities;

import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import java.io.File;
import java.io.IOException;
import thercn.ajide.Layout;
import thercn.ajide.R;
import thercn.ajide.utils.APPUtils;
import thercn.ajide.utils.Permission;
import thercn.ajide.utils.TLog;

public class IDEActivity extends AppCompatActivity {

	Layout mainLayout;
	public Menu actionBarMenu;
	boolean menuInited;

	public static String SDCARD = Environment.getExternalStorageDirectory().toString();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Permission.checkPermission(this);
		setContentView(R.layout.activity_main);
		initAppDir();
		mainLayout = new Layout(this);
		mainLayout.init();
	}

	private void initAppDir() {
		File appDir = new File(SDCARD + "/AJIDE");
		final File classPath = new File(SDCARD + "/AJIDE/ClassPath");
		if (!appDir.exists() || !classPath.exists()) {
			appDir.mkdir();
			classPath.mkdirs();
		}
		String[] files = new String[]{"android.jar","core-lambda-stubs.jar"};
		for (String i : files) {
			if (!new File(classPath.getAbsolutePath() + "/" + i).exists()) {
				APPUtils.exportAssets(this, i, classPath.getAbsolutePath());
			}
		}
		try {
			Runtime.getRuntime().exec("logcat >" + SDCARD + "/AJIDE/IDE.log");
		} catch (IOException e) {
			TLog.e( e);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.ide_menu, menu);
		for (int i = 0; i < menu.size(); i++) {
			menu.getItem(i).setEnabled(false);
		}
		MenuItemCompat.setShowAsAction(menu.findItem(R.id.undo), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
		MenuItemCompat.setShowAsAction(menu.findItem(R.id.build), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
        MenuItemCompat.setShowAsAction(menu.findItem(R.id.redo), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
		MenuItemCompat.setShowAsAction(menu.findItem(R.id.save), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
		actionBarMenu = menu;
		return super.onCreateOptionsMenu(menu);
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mainLayout.getCodeEditor().getCurrentFile() != null) {
			
			if (item.getItemId() == R.id.redo) {
				if (mainLayout.getCodeEditor().canRedo()) {
					mainLayout.getCodeEditor().redo();
				}
			} else if (item.getItemId() == R.id.build) {
				// Add the code for the build action here.
			} else if (item.getItemId() == R.id.undo) {
				if (mainLayout.getCodeEditor().canUndo()) {
					mainLayout.getCodeEditor().undo();
				}
			} else if (item.getItemId() == R.id.save) {
				try {
					mainLayout.getCodeEditor().saveFile();
				} catch (IOException e) {}
			}
			
		}
		return super.onOptionsItemSelected(item);
	}

	
	
	@Override
	protected void onPause() {
		if (mainLayout.getCodeEditor() != null) {
			try {
				mainLayout.getCodeEditor().saveFile();
			} catch (IOException e) {}
		}
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		try {
			if (mainLayout.getCodeEditor() != null) {
				mainLayout.getCodeEditor().setFile(mainLayout.getCodeEditor().getCurrentFile());
			}
		} catch (IOException e) {
			TLog.e(e);
		}
	}

	public Layout getLayout() {
		return mainLayout;
	}

	public void enableMenu() {
		if (mainLayout.getOpenedFiles().size() > 0) {
			for (int i = 0; i < actionBarMenu.size(); i++) {
				actionBarMenu.getItem(i).setEnabled(true);
			}
		}
	}

	public void disableMenu() {

		for (int i = 0; i < actionBarMenu.size(); i++) {
			actionBarMenu.getItem(i).setEnabled(false);
		}

	}


}
