package thercn.ajide.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class JavaCodeAnalysisService extends Service {
    
    public static final String TAG = "JavaCodeAnalysisService";
    
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

	@Override
	public void onCreate() {
		super.onCreate();
	}
    
	
}
