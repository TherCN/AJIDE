package thercn.ajide.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import java.io.IOException;
import java.util.List;
import thercn.ajide.IDEActivityLayout;
import thercn.ajide.R;
import thercn.ajide.utils.TLog;
import thercn.ajide.views.IDECodeEditor;

public class IDEActivity extends AppCompatActivity {

	IDEActivityLayout mainLayout;
	public Menu actionBarMenu;
	boolean menuInited;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		mainLayout = new IDEActivityLayout(this);
		mainLayout.inflateFileList(getIntent().getStringExtra("path"));
	}

	@Override
	public void onBackPressed() {
		mainLayout.saveAllFiles();
		super.onBackPressed();
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
		try {
			if (mainLayout.getCodeEditor() != null) {
				mainLayout.getCodeEditor().setFile(mainLayout.getCodeEditor().getCurrentFile());
			}
		} catch (IOException e) {
			TLog.e(e);
		}
	}

	public IDEActivityLayout getLayout() {
		return mainLayout;
	}




}
