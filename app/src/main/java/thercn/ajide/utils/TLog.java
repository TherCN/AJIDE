package thercn.ajide.utils;


import android.util.Log;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
public class TLog {

	public static File defaultFile = null;
	public static void initLogFile(String logFilePath) {
		if (defaultFile != null) {
			return;
		}
		defaultFile = new File(logFilePath);
		if (!defaultFile.exists() || !defaultFile.getParentFile().exists()) {
			try {
				defaultFile.getParentFile().mkdir();
				defaultFile.createNewFile();
			} catch (IOException e) {
				Log.e("AJIDE", getExceptionInfo(e));
			}
		}

	}

	public static String getStackTrace() {
		StringBuilder stackTrace = new StringBuilder();
		Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
		for (Thread s: map.keySet()) {
			StringBuilder sb = new StringBuilder();
			sb.append(s);
			for (StackTraceElement e : map.get(s)) {
				sb.append(e + "\n");
			}
			stackTrace.append(sb.toString() + "\n");
        }
		return stackTrace.toString();
	}

	public static void printStackTrace() {
		i("AJIDE",getStackTrace());
	}

    public static void d(String tag, Object... info) {
		writeLog(tag, "DEBUG", info);
	}

	public static void i(String tag, Object... info) {
		writeLog(tag, "INFO", info);
	}

	public static void e(String tag, Object... info) {
		writeLog(tag, "ERROR", info);
	}

	public static void fe(String tag, Object... info) {
		writeLog(tag, "FATAL ERROR", info);
	}

	public static void v(String tag, Object... info) {
		writeLog(tag, "VERBOSE", info);
	}

	public static void w(String tag, Object... info) {
		writeLog(tag, "WARNING", info);
	}

	public static String getExceptionInfo(Throwable exception) {
		StringBuilder sb = new StringBuilder(exception.getMessage() + "\n");
		for (StackTraceElement e : exception.getStackTrace()) {
			sb.append(e + "\n");
		}
		if (exception.getCause() != null) {
			for (StackTraceElement e : exception.getCause().getStackTrace()) {
				sb.append(e + "\n");
			}
		}
		return sb.toString();
	}

    public static void e(String tag, Throwable th) {
		e("ERROR",getExceptionInfo(th));
	}
	
	public static void e(Throwable th) {
		e("ERROR", getExceptionInfo(th));
	}

	private static void writeLog(String tag, String level, Object... info) {
		try {
			FileWriter writeLogText = new FileWriter(defaultFile, true);
			SimpleDateFormat formatter =
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date(System.currentTimeMillis());
			writeLogText.write("-----------------------------------\n");
			for (Object o : info) {
				writeLogText.write("[" + formatter.format(date) + " " + level + "]" + "[" + tag + "]: " + o + "\n");
			}
			writeLogText.flush();
			writeLogText.close();
		} catch (IOException e) {
			Log.e("AIDE X", getExceptionInfo(e));
		}
	}
}


