package thercn.ajide.project.template;

import thercn.ajide.project.ProjectTemplate;

public enum AndroidProjectTemplate implements ProjectTemplate {
	StandardAndroidProject("templates/android/BaseAndroidProject.zip"),
	StandardAndroidXProject("templates/android/BaseAndroidXProject.zip");
	public String templateName;

	AndroidProjectTemplate(String templateName) {
		this.templateName = templateName;
	}
}

