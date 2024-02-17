package thercn.ajide.project;

import android.widget.Toast;
import java.io.File;
import thercn.ajide.IDEApplication;

public abstract class Project {

	int BUILD_SUCCESS = 0;
	int BUILD_FAILED = 1;

    public abstract boolean build();

	public abstract boolean runTask(String task);

	public abstract String getProjectInfo();

	public static boolean isGradleProject(File dir) {
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().equals("gradlew") ||
				files[i].getName().equals("gradlew.bat") ||
				files[i].getName().contains("gradle")) {
				return true;
			}
		}
		return false;
	}

	public void setupGradle(String version) {
		File gradleDir = new File(IDEApplication.context
								  .getFilesDir()
								  .getAbsolutePath() + "/.gradle");
		gradleDir.mkdirs();
		Toast.makeText(IDEApplication.context, gradleDir.getAbsolutePath(), Toast.LENGTH_SHORT).show();
	}

	interface NewProject {

		public void setTemplate(ProjectTemplate template);

		public void setJDKVersion(int version);

		public void create();
	}
}
