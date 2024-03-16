package thercn.ajide.activities;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import thercn.ajide.IDEActivityLayout;
import thercn.ajide.R;
import thercn.ajide.project.ProjectUtils;

public class IDEActivity extends AppCompatActivity {

	IDEActivityLayout mainLayout;
	public Menu actionBarMenu;
	boolean menuInited;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initIDEUI();
	}

	private void initIDEUI() {
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		ProjectUtils.setProjectPath(getIntent().getStringExtra("path"));
		mainLayout = new IDEActivityLayout(this);
		mainLayout.init();
	}

	public boolean isDarkMode() {
		int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
		return nightModeFlags == Configuration.UI_MODE_NIGHT_YES;
	}

	@Override
	public void onBackPressed() {
		if (mainLayout.getDrawerLayout().isOpen()) {
			mainLayout.getDrawerLayout().close();
			return;
		}
		mainLayout.saveAllFiles();
		AlertDialog dialog = new MaterialAlertDialogBuilder(this)
			.setMessage("是否要退出项目？")
			.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dia, int which) {
					IDEActivity.super.onBackPressed();
				}
			})
			.setNegativeButton(android.R.string.cancel, null)
			.create();
		dialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.ide_menu, menu);
		MenuItemCompat.setShowAsAction(menu.findItem(R.id.undo), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
		MenuItemCompat.setShowAsAction(menu.findItem(R.id.build), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
        MenuItemCompat.setShowAsAction(menu.findItem(R.id.redo), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
		MenuItemCompat.setShowAsAction(menu.findItem(R.id.save), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
		actionBarMenu = menu;
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mainLayout.getCodeEditor() != null) {
			//因为Java语法被逼无奈使用if else
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
				mainLayout.saveAllFiles();
				Toast.makeText(getApplication(), "已保存所有文件", Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(getApplication(), "请打开一个文件！", Toast.LENGTH_SHORT).show();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		mainLayout.saveAllFiles();
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mainLayout != null) {
			mainLayout.refershFileList();
		}
	}
	public IDEActivityLayout getLayout() {
		return mainLayout;
	}


}
