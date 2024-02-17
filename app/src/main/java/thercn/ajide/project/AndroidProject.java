package thercn.ajide.project;

import thercn.ajide.project.template.AndroidProjectTemplate;

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

	public class CreateProject implements NewProject {
		String projectDir;
		public CreateProject(String projectDir) {
			this.projectDir = projectDir;
		}
		
		@Override
		public void create() {
			
		}

		@Override
		public void setTemplate(ProjectTemplate template) {
			
		}

		@Override
		public void setJDKVersion(int version) {
		
			
		}
	}
}
