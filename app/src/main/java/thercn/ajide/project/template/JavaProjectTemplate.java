package thercn.ajide.project.template;

import thercn.ajide.project.ProjectTemplate;

public enum JavaProjectTemplate implements ProjectTemplate {
	JavaConsoleProject(0),
	JavaGuiProject(1);
	public int templateIndex;
	JavaProjectTemplate(int i) {
		templateIndex = i;
	}
}

