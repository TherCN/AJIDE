package thercn.ajide.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import thercn.ajide.R;

public class CreateProjectView {

	public static final String PROJECT_TYPE_ANDROID = "Android";
	public static final String PROJECT_TYPE_JAVA = "Java";
	Context context;
	View v;
	public TextInputEditText projectName;
	public TextInputEditText packageName;
	TextInputLayout TIL1;
	TextInputLayout TIL2;
	CheckBox multiLanguage;
	RadioGroup projectType;
	RadioButton androidBtn;
	RadioButton javaBtn;
	Spinner javaVersion;
	Spinner minSdkVersion;
	Spinner targetSdkVersion;
	CheckBox enableJNI;
	CheckBox enableAndroidX;

	int minSdk;
	int javaVer;
	int targetSdk;

    public CreateProjectView(Context context) {
		this.context = context;
		init();
	}

	private void init() {
		v = LayoutInflater.from(context).inflate(R.layout.create_project, null);
		initWidget();
		initEvent();
	}

	public View getView() {
		return v;
	}

	private void initWidget() {
		TIL1 = v.findViewById(R.id.project_TIL1);
		TIL2 = v.findViewById(R.id.project_TIL2);
		projectType = v.findViewById(R.id.projectType_RG);
		projectName = v.findViewById(R.id.createProjectName);
		packageName = v.findViewById(R.id.createProjectPackageName);
		androidBtn = v.findViewById(R.id.projectAndroid);
		javaBtn = v.findViewById(R.id.projectJava);
		javaVersion = v.findViewById(R.id.javaVersion);
		minSdkVersion = v.findViewById(R.id.minSdkVersion);
		targetSdkVersion = v.findViewById(R.id.targetSdkVersion);
		enableJNI = v.findViewById(R.id.jni);
		enableAndroidX = v.findViewById(R.id.androidX);
		androidBtn.setChecked(true);
	}

	private void initEvent() {
		javaBtn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					minSdkVersion.setEnabled(false);
					targetSdkVersion.setEnabled(false);
					enableAndroidX.setChecked(false);
					enableAndroidX.setEnabled(false);

				}
			});
		androidBtn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					minSdkVersion.setEnabled(true);
					targetSdkVersion.setEnabled(true);
					enableAndroidX.setEnabled(true);
				}
			});
		Integer[] javaVersions = new Integer[]{8,11,17};
		Integer[] sdkVersions = new Integer[]{21,22,23,24,25,26,27,28,29,30,31,32,33,34};
		final ArrayAdapter<Integer> javaVersionAdapter = new ArrayAdapter<Integer>(context, android.R.layout.simple_spinner_item, javaVersions);
		final ArrayAdapter<Integer> sdkVersionAdapter = new ArrayAdapter<Integer>(context, android.R.layout.simple_spinner_item, sdkVersions);
		javaVer = javaVersionAdapter.getItem(0);
		minSdk = sdkVersionAdapter.getItem(0);
		targetSdk = sdkVersionAdapter.getItem(0);
		javaVersion.setAdapter(javaVersionAdapter);
		minSdkVersion.setAdapter(sdkVersionAdapter);
		targetSdkVersion.setAdapter(sdkVersionAdapter);

		javaVersion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
					javaVer = javaVersionAdapter.getItem(position);
				}
				public void onNothingSelected(AdapterView<?> parent) {}
			});
		minSdkVersion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
					minSdk = sdkVersionAdapter.getItem(position);
				}
				public void onNothingSelected(AdapterView<?> parent) {}
			});
		targetSdkVersion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
					targetSdk = sdkVersionAdapter.getItem(position);
				}
				public void onNothingSelected(AdapterView<?> parent) {}
			});

		projectName.setError("项目名称必须填写！");
		packageName.setError("项目包名必须填写！");
		projectName.addTextChangedListener(new TextWatcher() {
				public void afterTextChanged(Editable s) {}

				public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

				public void onTextChanged(CharSequence s, int start, int before, int count) {
					if (getProjectName().isEmpty()) {
						TIL1.setErrorEnabled(true);
						projectName.setError("项目名称必须填写！");
					} else {
						TIL1.setErrorEnabled(false);
					}
				}
				
		});
		
		packageName.addTextChangedListener(new TextWatcher() {
				public void afterTextChanged(Editable s) {}

				public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

				public void onTextChanged(CharSequence s, int start, int before, int count) {
					if (getPackageName().isEmpty()) {
						TIL2.setErrorEnabled(true);
						packageName.setError("项目包名必须填写！");
					} else {
						TIL2.setErrorEnabled(false);
					}
				}

			});
	}

	public String getProjectName() {
		return projectName.getText().toString();
	}

	public TextInputLayout getProjectNameTextInputLayout() {
		return v.findViewById(R.id.project_TIL1);
	}

	public TextInputLayout getPackageNameTextInputLayout() {
		return v.findViewById(R.id.project_TIL2);
	}

	public String getPackageName() {
		return packageName.getText().toString();
	}

	public String getProjectType() {
		if (androidBtn.isChecked()) {
			return PROJECT_TYPE_ANDROID;
		} else {
			return PROJECT_TYPE_JAVA;
		}
	}

	public int getMinSdkVersion() {
		return minSdk;
	}

	public int getTargetSdkVersion() {
		return targetSdk;
	}

	public int getJavaVersion() {
		return javaVer;
	}

	public boolean isEnabledJNI() {
		return enableJNI.isChecked();
	}

	public boolean isEnabledAndroidX() {
		return enableAndroidX.isChecked();
	}
}
