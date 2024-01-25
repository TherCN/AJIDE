package thercn.ajide;

public interface Project {
	
	int BUILD_SUCCESS = 0;
	int BUILD_FAILED = 1;
	
	public boolean isProject();
	
    public boolean build();
    
	public boolean runTask(String task);
	
	public String getProjectInfo();
    
	interface NewProject {
		
		public void setTemplate(String template);
		
		public void setJDKVersion(int version);
		
		public void create();
	}
}
