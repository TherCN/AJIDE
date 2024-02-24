package thercn.ajide.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class FileDownloader {

	private OnDownloadProgressUpdateCallback callbak;
	private URL url;
	private String outputPath;
	private int timeOut = 3000;

    public FileDownloader(URL url, String outputPath) {
		this.url = url;
		this.outputPath = outputPath;
	}

	public int getLength() throws IOException {
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setConnectTimeout(timeOut);
		Map<String, List<String>> map = httpConn.getHeaderFields();
		return Integer.parseInt(map.get("Content-Length").get(0));
	}

	public void download() throws IOException {
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setConnectTimeout(timeOut);
		int responseCode = httpConn.getResponseCode();
		// 检查HTTP响应代码是否为200（成功）
		if (responseCode == HttpURLConnection.HTTP_OK) {
			// 创建保存文件的目录
			File saveDirFile = new File(outputPath).getParentFile();
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			Map<String, List<String>> map = httpConn.getHeaderFields();
			int downloadLength = Integer.parseInt(map.get("Content-Length").get(0));
			// 使用BufferedInputStream和FileOutputStream来保存文件

			BufferedInputStream in =
				new BufferedInputStream(httpConn.getInputStream()); 
			FileOutputStream fos = new FileOutputStream(outputPath);
			byte[] buffer = new byte[1024];
			int bytesRead;
			int count = 0;
			while ((bytesRead = in.read(buffer)) != -1) {
				fos.write(buffer, 0, bytesRead);
				count += bytesRead;
				if (callbak != null) {
					callbak.onProgressUpdate(count, downloadLength);
				}
			}
		}
		httpConn.disconnect();
	}

	public void setCallbak(OnDownloadProgressUpdateCallback callbak) {
		this.callbak = callbak;
	}

	public interface OnDownloadProgressUpdateCallback {
		void onProgressUpdate(int currentProgress, int totalProgress);
	}

}
