package thercn.ajide.services;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;
import thercn.ajide.R;
import thercn.ajide.utils.APPUtils;
import android.widget.ActionMenuView.LayoutParams;
import android.widget.TextView;
import thercn.ajide.utils.AsyncProcess;
import thercn.ajide.IDEApplication;
import thercn.ajide.utils.LogPrintStream;
import thercn.ajide.utils.TLog;

public class LogPrintService extends Service {

    public static final String TAG = "LogPrintService";
    private WindowManager windowManager;
    private View floatingView;
	private boolean isVisible = true;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Inflating the floating view layout
		setTheme(R.style.IDEMainTheme);
        floatingView = LayoutInflater.from(this).inflate(R.layout.log_print_window, null);

		final LinearLayout rootLayout = floatingView.findViewById(R.id.window_root_layout);
		final LinearLayout subLayout = floatingView.findViewById(R.id.window_sub_layout);
        // Setting the layout parameters
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
			WindowManager.LayoutParams.MATCH_PARENT,
			APPUtils.dip2px(this, 600),
			WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
			WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
			PixelFormat.TRANSLUCENT);

        // Getting windows services and adding the floating view to it
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowManager.addView(floatingView, params);

        // Setting up the ViewPager and TabLayout
        ViewPager viewPager = floatingView.findViewById(R.id.log_window_vp);
        TabLayout tabLayout = floatingView.findViewById(R.id.log_window_tabs);

        // Creating a ViewPager adapter
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        viewPager.setAdapter(adapter);

		// Connecting ViewPager with TabLayout
        tabLayout.setupWithViewPager(viewPager);

		final TextView logView = adapter.getView(0).findViewById(R.id.log_text);
		logView.setText("已启动日志记录\n");
		IDEApplication.printStream.setOnPrintCallback(new LogPrintStream.OnPrintCallback() {
				public void onPrint(String text) {
					final String logText = text;
					
					APPUtils.getMainActivity().runOnUiThread(new Runnable(){
							@Override
							public void run() {
								//TLog.i("",logText);
								logView.append(logText + "\n");
							}
						});
				}
			});
		AsyncProcess logcatProcess = new AsyncProcess("logcat");
		logcatProcess.redirectErrorStream(true);
		logcatProcess.setCommandOutputListener(new AsyncProcess.CommandOutputListener() {
				public void onCommandOutputUpdate(String output) {
					System.out.print("[logcat]" + output);
				}
			});
		logcatProcess.start();
        // Listeners for the drag movement of the floating widget
        final Button ibtn = floatingView.findViewById(R.id.window_button);
		ibtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (isVisible) {
						// 收缩布局
						ibtn.setText("关");
						subLayout.setVisibility(View.GONE);
						params.width = WindowManager.LayoutParams.WRAP_CONTENT;
						params.height = WindowManager.LayoutParams.WRAP_CONTENT;
					} else {
						// 展开布局
						ibtn.setText("开");
						subLayout.setVisibility(View.VISIBLE);
						params.width = params.MATCH_PARENT;
						params.height = APPUtils.dip2px(LogPrintService.this, 600);
					}
					isVisible = !isVisible;
					windowManager.updateViewLayout(floatingView, params); // 更新浮动窗口的布局参数
				}
			});
		ibtn.setOnTouchListener(new View.OnTouchListener() {
				private int initialX;
				private int initialY;
				private float initialTouchX;
				private float initialTouchY;

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
						case MotionEvent.ACTION_DOWN:
							initialX = params.x;
							initialY = params.y;
							initialTouchX = event.getRawX();
							initialTouchY = event.getRawY();
							return false;
						case MotionEvent.ACTION_UP:
							// When the drag is ended switching the state of the widget
							// collapsedView.setVisibility(View.GONE);
							return false;
						case MotionEvent.ACTION_MOVE:
							// Calculating the X and Y coordinates of the view
							params.x = initialX + (int) (event.getRawX() - initialTouchX);
							params.y = initialY + (int) (event.getRawY() - initialTouchY);

							// Updating the layout with new X & Y coordinate
							windowManager.updateViewLayout(floatingView, params);
							return false;
					}
					return false;
				}
			});


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (floatingView != null) windowManager.removeView(floatingView);
    }

    private class ViewPagerAdapter extends androidx.viewpager.widget.PagerAdapter {

        private List<View> views = new ArrayList<>();
		private String titles[] = {
			"IDE日志",
			"构建输出"
		};
        public ViewPagerAdapter() {
            views.add(LayoutInflater.from(LogPrintService.this).inflate(R.layout.ide_log_view, null));
            views.add(LayoutInflater.from(LogPrintService.this).inflate(R.layout.build_output_view, null));
        }

        @Override
        public int getCount() {
            return views.size();
        }
		
		public View getView(int index) {
			return views.get(index);
		}

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = views.get(position);
            container.addView(view);
			return view;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return titles[position];
		}
		
		
	}


}
