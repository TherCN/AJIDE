package thercn.ajide.utils;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import android.view.View;

public class LogPrintStream extends PrintStream {

    public static final String TAG = "LogPrintStream";
    private StringBuilder buffer;
	private OnPrintCallback callback;
	public LogPrintStream() {
		super(new OutputStream() {
				@Override
				public void write(int b) {
				}
			});
        this.buffer = new StringBuilder();
	}

	public void setOnPrintCallback(OnPrintCallback callback) {
		this.callback = callback;
	}

	public OnPrintCallback getOnPrintCallback() {
		return this.callback;
	}

	public interface OnPrintCallback {
		void onPrint(String text);
	}

	@Override
    public void write(byte[] buf, int off, int len) {
        //避免重复输出
		if (Arrays.toString(Thread.currentThread().getStackTrace()).contains("PrintStream.newLine")) return;
        String text = new String(buf, off, len);
		if (callback != null) {
			callback.onPrint(text);
		}
    }

	@Override
	public void flush() {
		buffer.setLength(0);
	}

}
