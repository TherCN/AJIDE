package thercn.ajide.utils;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class AsyncProcess {

	String[] cmd;
	ProcessBuilder processBuilder;
	Process commandProcess;
	CommandOutputListener listener;

	private AsyncProcess() {};

    public AsyncProcess(String... cmd) {
		this.cmd = cmd;
		processBuilder =  new ProcessBuilder();
	}

	public AsyncProcess(List<String> cmd) {
		this.cmd = cmd.toArray(new String[0]);
	}

	public interface CommandOutputListener {
		void onCommandOutputUpdate(String output);
	}
	
	public void redirectErrorStream(boolean yes) {
		processBuilder.redirectErrorStream(yes);
	}
	
	public void prepare() {
		processBuilder.command(cmd);
	}

	public void setCommandOutputListener(CommandOutputListener listener) {
		this.listener = listener;
	}

	public void start() {
		Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						commandProcess = processBuilder.start();
					} catch (IOException e) {}
					readOutput();
				}
			});
		thread.start();
		
	}

	private void readOutput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(commandProcess.getInputStream()));
		String temp;
		try {
			while ((temp = br.readLine()) != null) {
				// 拼接换行符
				if (listener != null) {
					listener.onCommandOutputUpdate(temp);
				}
			}
			br.close();

		} catch (IOException e) {
			Log.wtf("", e);
		}
	}

}
