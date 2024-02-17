package thercn.ajide.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import thercn.ajide.R;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;

public class CreateProjectView {

	Context context;
	View v;
	
	EditText projectName;
	EditText packageName;
	CheckBox multiLanguage;
	RadioButton androidBtn;
	RadioButton javaBtn;
	RadioButton kotlinBtn;
	Spinner javaVersion;
	Spinner minSdkVersion;
	Spinner targetSdkVersion;
	CheckBox enableJNI;
	CheckBox enableAndroidX;
	
    public CreateProjectView(Context context) {
		this.context = context;
		init();
	}
	
	private void init() {
		v = LayoutInflater.from(context).inflate(R.layout.create_project,null);
		initWidget();
		initEvent();
		
	}
	
	public View getView() {
		return v;
	}
	
	private void initWidget() {
		projectName = v.findViewById(R.id.createProjectName);
		packageName = v.findViewById(R.id.createProjectPackageName);
		multiLanguage = v.findViewById(R.id.multiLanguage);
		androidBtn = v.findViewById(R.id.projectAndroid);
		javaBtn = v.findViewById(R.id.projectJava);
		kotlinBtn = v.findViewById(R.id.projectKotlin);
		javaVersion = v.findViewById(R.id.javaVersion);
		minSdkVersion = v.findViewById(R.id.minSdkVersion);
		targetSdkVersion = v.findViewById(R.id.targetSdkVersion);
		enableJNI = v.findViewById(R.id.jni);
		enableAndroidX = v.findViewById(R.id.androidX);
	}
	
	private void initEvent() {
		
	}
	
}
