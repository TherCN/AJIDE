package thercn.ajide.file;

import java.io.File;
import android.os.FileUtils;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOperation {

    public static void delete(File file) {
		file.delete();
	}

	public static void copy(File file, File target) throws IOException {
		InputStream in = new FileInputStream(file);
		OutputStream out = new FileOutputStream(target);
		FileUtils.copy(in, out);
	}
	
	public static void move(File file, File target) throws IOException {
		copy(file,target);
		file.delete();
	}
	
	public static void rename(File source, File target) {
		if (!target.exists()) {
			target.mkdirs();
		}
		source.renameTo(target);
	}
	
	public static void createFile(String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			File parent = new File(file.getParent());
			if (!parent.exists()) {
				parent.mkdirs();
			}
			file.createNewFile();
		}
	}
	
	public static void createFloder(String path) {
		File file = new File(path);
		file.mkdirs();
	}
}
