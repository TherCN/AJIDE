package thercn.ajide.utils;


import android.util.Log;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
public class TLog {

	public static File logFile = new File("/sdcard/AIDELog.txt");
	public static File soutFile = new File("/sdcard/AIDEsout.txt");
	public static void initLogFile() {
		if (!logFile.exists() && !soutFile.exists()) {
			try {
				logFile.createNewFile();
				soutFile.createNewFile();
			} catch (IOException e) {
				Log.e("AJIDE", getExceptionInfo(e));
			}
		}
	}

	public static String getStackTrace() {
		//return sb.toString();
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
		System.out.println(getStackTrace());
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

	public static void e(String tag, Throwable exception, Object... info) {
		StringBuilder exceptionInfo = new StringBuilder(info + "\n");
		exceptionInfo.append(getExceptionInfo(exception));

		writeLog(tag, "ERROR", exceptionInfo);
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
		StringBuilder sb = new StringBuilder();
		for (StackTraceElement e : exception.getStackTrace()) {
			sb.append(e + "\n");
		}
		if (exception.getCause() != null) {
			for (StackTraceElement e : exception.getCause().getStackTrace()) {
				sb.append(e + "\n");
			}
		}
		if (exception.getSuppressed() != null) {
			Throwable[] th = exception.getSuppressed();
			for (int i = 0; i < th.length; i++) {
				for (StackTraceElement e : th[i].getCause().getStackTrace()) {
					sb.append(e + "\n");
				}
			}
		}
		return sb.toString();
	}

	public static void e(Throwable th) {
		e("ERROR", getExceptionInfo(th));
	}

	private static void writeLog(String tag, String level, Object... info) {
		try {
			FileWriter writeLogText = new FileWriter(logFile, true);
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
