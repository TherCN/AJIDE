package thercn.ajide.project.template;

import thercn.ajide.project.ProjectTemplate;

public enum AndroidProjectTemplate implements ProjectTemplate {
	StandardAndroidProject(0),
	StandardAndroidXProject(1);
	public int templateIndex;
	AndroidProjectTemplate(int i) {
		templateIndex = i;
	}
}

