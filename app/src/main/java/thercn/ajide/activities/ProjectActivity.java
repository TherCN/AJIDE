package thercn.ajide.activities;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import thercn.ajide.R;
import thercn.ajide.adapter.ProjectViewAdapter;
import thercn.ajide.project.Project;
import thercn.ajide.utils.APPUtils;
import thercn.ajide.utils.Permission;
import thercn.ajide.utils.TLog;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProjectActivity extends AppCompatActivity {

	public static String SDCARD = Environment.getExternalStorageDirectory().toString();
	public static File appDir = new File(SDCARD + "/AJIDE");
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project);
		Permission.checkPermission(this);
		if (!Permission.isPermissionGranted(this)) {
			throw new IllegalAccessError("请同意权限申请！");
		}
		initAppDir();
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle(R.string.app_name);
		
		RecyclerView projectView = findViewById(R.id.projectList);
		projectView.setAdapter(new ProjectViewAdapter<String>(this,getProjects()));
		projectView.setLayoutManager(new GridLayoutManager(this,2));
		
		BottomNavigationView bnv = findViewById(R.id.bnv);
		
	}
    
	private void initAppDir() {
		final File classPath = new File(SDCARD + "/AJIDE/ClassPath");
		if (!appDir.exists() || !classPath.exists()) {
			appDir.mkdir();
			classPath.mkdirs();
		}
		String[] exportFiles = new String[]{"android.jar","core-lambda-stubs.jar"};
		for (String i : exportFiles) {
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
	
	private long firstBackTime;
	@Override
	public void onBackPressed() {
		if (System.currentTimeMillis() - firstBackTime > 1000) {
			Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
			firstBackTime = System.currentTimeMillis();
			return;
		}

		super.onBackPressed();
	}
	
	private List<String> getProjects() {
		File[] files = APPUtils.getFiles(appDir.getAbsolutePath());
		List<String> projects = new ArrayList<>();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory() && Project.isGradleProject(files[i])) {
				projects.add(files[i].getAbsolutePath());
			}
		}
		return projects;
	}
    
    
}
