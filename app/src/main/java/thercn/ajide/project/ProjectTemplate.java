package thercn.ajide.project;

public interface ProjectTemplate {
    public static enum AndroidProjectTemplate implements ProjectTemplate {
		StandardAndroidProject(0),
		StandardAndroidXProject(1);
		
		public int templateIndex;
		public AndroidProjectTemplate(int i) {
			templateIndex = i;
		}
	}
	
	public static enum JavaProjectTemplate implements ProjectTemplate {
		JavaConsoleProject(0),
		JavaGuiProject(1);
		
		public int templateIndex;
		public JavaProjectTemplate(int i) {
			templateIndex = i;
		}
	}
    
    
}
