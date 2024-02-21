package thercn.ajide.project;

import java.io.File;
import java.io.IOException;
import thercn.ajide.IDEApplication;
import thercn.ajide.file.FileOperation;
import thercn.ajide.project.template.AndroidProjectTemplate;
import thercn.ajide.utils.APPUtils;
import thercn.ajide.utils.TLog;

public class AndroidProject extends Project {
	String androidProjectDir;
	public AndroidProject(String projectDir) {
		androidProjectDir = projectDir;
	}

	@Override
	public boolean build() {
		return true;
	}

	@Override
	public String getProjectInfo() {
		return null;
	}

	@Override
	public boolean runTask(String task) {
		return false;
	}

	public static class CreateProject implements NewProject {

		private String projectName;
		private String packageName;
		private AndroidProjectTemplate template;
		private int javaVersion;
		private int minSdkVersion;
		private int targetSdkVersion;
		private File projectDir;
		public CreateProject(String projectName) {
			this.projectName = projectName;
		}

		@Override
		public void create() throws IOException {
			projectDir = new File(IDEApplication.APPDIR + "/" + projectName);
			try {
				APPUtils.unzipFromAssets(IDEApplication.getContext(), 
										 template.templateName,
										 projectDir.getAbsolutePath());
			} catch (IOException e) {
				TLog.e(e);
			}
			setProjectName(projectDir + "/settings.gradle");
			setPackageName1(projectDir + "/app/build.gradle");
		}

		private void setProjectName(String settingsFile) throws IOException {
			String settings = APPUtils.readFile(settingsFile);
			settings = settings.replace("{AppName}", projectName);
			APPUtils.writeTextToFile(settingsFile, settings);
		}

		private void setPackageName1(String buildScriptFile) throws IOException {
			String buildScript = APPUtils.readFile(buildScriptFile);
			APPUtils.writeTextToFile(buildScriptFile, buildScript.replace("{PackageName}", packageName));
			File file = new File(projectDir.getAbsolutePath() + "/app/src/main/java/{PackageName}");
			File file2 = new File(projectDir.getAbsolutePath() + "/app/src/main/java/" + packageName.replace(".", "/"));
			FileOperation.rename(file, file2);
			file = new File(file2.getAbsolutePath() + "/MainActivity.java");
			String actStr = APPUtils.readFile(file.getAbsolutePath());
			APPUtils.writeTextToFile(file.getAbsolutePath(), actStr.replace("{PackageName}", packageName));
			
			file = new File(projectDir.getAbsolutePath() + "/app/src/main/AndroidManifest.xml");
			String manifest = APPUtils.readFile(file.getAbsolutePath());
			APPUtils.writeTextToFile(file.getAbsolutePath(), manifest.replace("{PackageName}", packageName));
		}
		
		@Override
		public CreateProject setPackageName(String packageName) {
			this.packageName = packageName;
			return this;
		}

		@Override
		public CreateProject setTemplate(ProjectTemplate template) {
			this.template = (AndroidProjectTemplate)template;
			return this;
		}

		@Override
		public CreateProject setJavaVersion(int version) {
			this.javaVersion = version;
			return this;
		}

		public CreateProject setMinSdkVersion(int version) {
			this.minSdkVersion = version;
			return this;
		}

		public CreateProject setTargetSdkVersion(int version) {
			this.targetSdkVersion = version;
			return this;
		}


	}
}
