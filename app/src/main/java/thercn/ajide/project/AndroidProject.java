package thercn.ajide.project;

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

	


    
    
    
}
