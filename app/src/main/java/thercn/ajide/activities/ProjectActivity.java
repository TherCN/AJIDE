package thercn.ajide.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import thercn.ajide.R;
import thercn.ajide.adapter.ProjectItemViewAdapter;
import thercn.ajide.adapter.ProjectViewAdapter;
import thercn.ajide.project.AndroidProject;
import thercn.ajide.project.Project;
import thercn.ajide.project.template.AndroidProjectTemplate;
import thercn.ajide.utils.APPUtils;
import thercn.ajide.utils.AsyncProcess;
import thercn.ajide.utils.FileDownloader;
import thercn.ajide.utils.Permission;
import thercn.ajide.utils.TLog;
import thercn.ajide.views.CreateProjectView;
import thercn.ajide.views.DisableScrollViewPager;
import com.google.android.material.tabs.TabLayout;
import android.content.Intent;
import thercn.ajide.services.LogPrintService;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.app.ActivityManager;
import android.content.Context;
import androidx.viewpager.widget.ViewPager;

public class ProjectActivity extends AppCompatActivity {

	public static String SDCARD = Environment.getExternalStorageDirectory().toString();
	public static File appDir = new File(SDCARD + "/AJIDE");
	RecyclerView projectView;
	DisableScrollViewPager pager;
	File jdkHome;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project);

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle(R.string.app_name);

		View main = getLayoutInflater().inflate(R.layout.project_items, null);
		projectView = main.findViewById(R.id.projectList);
		Permission.checkPermission(this);

		if (Permission.isPermissionGranted(this)) {
			initAppDir();
			TLog.initLogFile(appDir.getAbsolutePath() + "/AJIDE.log");
			projectView.setAdapter(new ProjectItemViewAdapter<String>(this, getProjects()));
			projectView.setLayoutManager(new LinearLayoutManager(this));
			initUsrPath();
		}

		View settings = getLayoutInflater().inflate(R.layout.project_setting, null);
		pager = findViewById(R.id.project_viewpager);

		ProjectViewAdapter adapter = new ProjectViewAdapter();
		pager.setAdapter(adapter);
		adapter.addView(main);
		adapter.addView(settings);
		
		pager.setCurrentItem(0);
		initBottomView();

		if (APPUtils.getMainActivity() == null) {
			APPUtils.setMainActivity(this);
			startService(new Intent(this, LogPrintService.class));
		}
	}
	
	<T> T 方法名(T arg) { 
		return arg;
	}

	private void initAppDir() {
		final File classPath = new File(SDCARD + "/AJIDE/ClassPath");

		final File projectsPath = new File(appDir, "Projects");
		final File mavenPath = new File(appDir, "Maven");
		if (!appDir.exists() ||
			!classPath.exists() ||
			!projectsPath.exists() ||
			!mavenPath.exists()) {
			appDir.mkdir();
			classPath.mkdirs();
			projectsPath.mkdirs();
			mavenPath.mkdirs();
		}
		String[] exportFiles = new String[]{"android.jar","core-lambda-stubs.jar"};
		for (String i : exportFiles) {
			if (!new File(classPath.getAbsolutePath() + "/" + i).exists()) {
				APPUtils.exportAssets(this, i, classPath.getAbsolutePath());
			}
		}
		projectView.requestFocus();
		try {
			Runtime.getRuntime().exec("logcat >" + SDCARD + "/AJIDE/IDE.log");
		} catch (IOException e) {
			TLog.e(e);
		}
	}

	public void initUsrPath() {
		View v = getLayoutInflater().inflate(R.layout.progress_dialog, null);
		File usr = new File(getFilesDir(), "/usr/opt");
		jdkHome = new File(usr, "/openjdk-17");
		final File jdkArchiveFile = new File(jdkHome, "/OpenJDK17-AJIDE.tar.gz");
		if (!usr.exists()) {
			usr.mkdirs();
		}
		final ProgressBar pbar = v.findViewById(R.id.progress);
		final TextView progress = v.findViewById(R.id.progressText);
		final AlertDialog dialog = new MaterialAlertDialogBuilder(this)
			.setTitle("正在下载OpenJDK")
			.setView(v)
			.setCancelable(false)
			.create();
		final String openJDKDownloadUrl = "https://mirror.ghproxy.com/https://github.com/TherCN/openjdk17-build/releases/download/1.0/OpenJDK17-AJIDE.tar.gz";
		if (jdkArchiveFile.exists()) {
			if (!APPUtils.checkSha1(jdkArchiveFile.getAbsolutePath(), "f04888cc03d518ec568f761f5a6403629a5380c3")) {
				try {
					Runtime.getRuntime().exec(new String[]{"rm","-rf",jdkHome.getAbsolutePath() });
				} catch (IOException e) {
					TLog.e(e);
				}
				initUsrPath();
			} else {
				AsyncProcess process = new AsyncProcess("tar", "xvf", jdkArchiveFile.getAbsolutePath(), "-C", jdkHome.getAbsolutePath());
				process.redirectErrorStream(false);
				process.setCommandOutputListener(new AsyncProcess.CommandOutputListener() {
						int count;
						public void onCommandOutputUpdate(final String output) {
							runOnUiThread(new Runnable(){
									@Override
									public void run() {
										progress.setText(output);
										pbar.setProgress(count);
										count++;
										if (count == pbar.getMax()) {
											dialog.dismiss();
											jdkArchiveFile.delete();
											Toast.makeText(getApplication(), "解压完成", Toast.LENGTH_SHORT).show();
										}
									}
								});
						}
					});
				pbar.setMax(690);
				dialog.setTitle("正在解压");
				dialog.show();
				process.prepare();
				process.start();
			}
		}
		if (!jdkHome.exists()) {
			dialog.show();
			new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							final FileDownloader downloader = new FileDownloader(new URL(openJDKDownloadUrl), jdkArchiveFile.getAbsolutePath());

							final int length = downloader.getLength();
							runOnUiThread(new Runnable(){
									@Override
									public void run() {
										pbar.setMax(length);
									}
								});
							downloader.setCallbak(new FileDownloader.OnDownloadProgressUpdateCallback() {
									@Override
									public void onProgressUpdate(final int currentProgress, final int totalProgress) {
										// 使用runOnUiThread来更新UI
										runOnUiThread(new Runnable() {
												@Override
												public void run() {
													progress.setText(currentProgress + "/" + totalProgress);
													pbar.setProgress(currentProgress);
												}
											});
									}
								});
							downloader.download();
						} catch (Exception e) {
							TLog.e(e);
							runOnUiThread(new Runnable(){

									@Override
									public void run() {
										Toast.makeText(ProjectActivity.this, "发生错误", Toast.LENGTH_SHORT).show();
									}
								});
						} finally {
							// 使用runOnUiThread来关闭对话框
							runOnUiThread(new Runnable() {
									@Override
									public void run() {
										dialog.dismiss();
										initUsrPath();
									}
								});
						}
					}
				}).start();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (Permission.isPermissionGranted(this)) {
			refreshProjects();
		}
	}

	private void refreshProjects() {
		projectView.setAdapter(new ProjectItemViewAdapter<String>(this, getProjects()));
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
		File[] files = APPUtils.getFiles(appDir + "/Projects");
		List<String> projects = new ArrayList<>();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory() && Project.isGradleProject(files[i])) {
				projects.add(files[i].getAbsolutePath());
			}
		}
		return projects;
	}

	private void initBottomView() {
		BottomNavigationView bnv = findViewById(R.id.bnv);
		bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
				public boolean onNavigationItemSelected(MenuItem item) {
					if (item.getItemId() == R.id.main) {
						pager.setCurrentItem(0);
					} else if (item.getItemId() == R.id.setting) {
						pager.setCurrentItem(1);
					} else {
						return false;
					}
					return true;
				}
			});
		bnv.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
				public void onNavigationItemReselected(MenuItem item) {
					if (item.getItemId() == R.id.main) {
						pager.setCurrentItem(0);
					} else if (item.getItemId() == R.id.setting) {
						pager.setCurrentItem(1);
					} else {
						return;
					}
				}
			});

		FloatingActionButton createProject = findViewById(R.id.fab);
		createProject.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					showCreateProjectDialog();
				}
			});
	}
	private void showCreateProjectDialog() {
		final CreateProjectView createProjectView = new CreateProjectView(this);
		final AlertDialog dialog = new MaterialAlertDialogBuilder(this)
			.setTitle("创建项目")
			.setView(createProjectView.getView())
			.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dia, int which) {}
			})
			.setNegativeButton(android.R.string.cancel, null)
			.create();
		dialog.show();
		dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {  
				@Override  
				public void onClick(View v) {  
					if (createProjectView.getProjectName().isEmpty() || createProjectView.getPackageName().isEmpty()) {
						return;
					}
					if (createProjectView.getProjectType().equals(createProjectView.PROJECT_TYPE_ANDROID)) {
						AndroidProject.CreateProject create = new AndroidProject.CreateProject(createProjectView.getProjectName()).
							setTemplate(createProjectView.isEnabledAndroidX() ? 
										AndroidProjectTemplate.StandardAndroidXProject : 
										AndroidProjectTemplate.StandardAndroidProject)
							.setPackageName(createProjectView.getPackageName())
							.setJavaVersion(createProjectView.getJavaVersion())
							.setMinSdkVersion(createProjectView.getMinSdkVersion())
							.setTargetSdkVersion(createProjectView.getTargetSdkVersion());
						try {
							create.create();
							refreshProjects();
						} catch (IOException e) {
							TLog.e(e);
							Toast.makeText(getApplication(), "在创建项目" + createProjectView.getProjectName() + "时发生错误，请到" + TLog.defaultFile + "查看日志", Toast.LENGTH_SHORT).show();
						}

					}
					dialog.dismiss();
				}  
			});
	}
}
