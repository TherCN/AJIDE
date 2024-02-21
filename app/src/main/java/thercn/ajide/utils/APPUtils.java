package thercn.ajide.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import android.content.DialogInterface;

public class APPUtils {
    
    public static File[] getFiles(String path) {
		File[] files = new File(path).listFiles();
		if (files == null) {
			return files;
		}
		Arrays.sort(files, new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					int typeCompare = o1.isFile() ? (o2.isFile() ? 0 : 1) 
						: (o2.isFile() ? -1 : 0);

					if (typeCompare != 0) {
						return typeCompare;
					} else {
						String name1 = o1.getName().toUpperCase();
						String name2 = o2.getName().toUpperCase();

						return name1.compareTo(name2);
					}
				}
			});
		return files;
	}

	public static void exportAssets(Context activity, String fileName,
									String outPath) {
		File outdir = new File(outPath);
		if (!outdir.exists()) {
			outdir.mkdirs();
		}
		try {
			InputStream inputStream = activity.getAssets().open(fileName);
			File outFile = new File(outdir, fileName);
			if (outFile.exists()) {
				return;
			}
			FileOutputStream fileOutputStream = new FileOutputStream(outFile);
			byte[] buffer = new byte[1024];
			int byteRead;
			while (-1 != (byteRead = inputStream.read(buffer))) {
				fileOutputStream.write(buffer, 0, byteRead);
			}
			inputStream.close();
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (IOException e) {
			Log.e("AIDE X", Log.getStackTraceString(e));
		}
	}

	public static String readFile(String filePath) throws IOException {
		return readerToString(new FileReader(new File(filePath)));
	}
	
	
	public static void unzipFromAssets(Context context, String zipFileName, String outputDir) throws IOException {
		AssetManager assetManager = context.getAssets();
		InputStream is = assetManager.open(zipFileName);
		ZipInputStream zis = new ZipInputStream(is);
		ZipEntry entry;
		while ((entry = zis.getNextEntry()) != null) {
			String fileName = entry.getName();
			File newFile = new File(outputDir + File.separator + fileName);
			if (entry.isDirectory()) {
				newFile.mkdirs();
			} else {
				File parentFile = newFile.getParentFile();
				if (!parentFile.exists()) {
					parentFile.mkdirs();
				}
				OutputStream os = new FileOutputStream(newFile);
				byte[] buffer = new byte[1024];
				int len;
				while ((len = zis.read(buffer)) > 0) {
					os.write(buffer, 0, len);
				}
				os.close();
			}
		}
		zis.closeEntry();
		zis.close();
	}
	
	
	public static String readerToString(Reader reader) {

		BufferedReader br = new BufferedReader(reader);
		StringBuilder sb = new StringBuilder();
		String temp = "";
		try {
			while ((temp = br.readLine()) != null) {
				// 拼接换行符
				sb.append(temp + "\n");
			}
			br.close();
		} catch (IOException e) {
			TLog.e(e);
		}
		return sb.toString();
	}

	public static String readFileFromZip(String zipFilePath, String fileNameToRead) throws IOException {
        File file = new File(zipFilePath);
        if (!file.exists()) {
            throw new FileNotFoundException("ZIP file does not exist");
        }
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
        ZipEntry entry;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while ((entry = zipInputStream.getNextEntry()) != null) {
            if (entry.getName().equals(fileNameToRead)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }
                break;
            }
        }
        zipInputStream.closeEntry();
        zipInputStream.close();
        return new String(byteArrayOutputStream.toByteArray());
    }

	public static void exportFileFromZip(String zipFilePath, String fileName, String outputPath) {
        try {
			ZipFile zipFile = new ZipFile(zipFilePath);
			FileOutputStream outputStream = new FileOutputStream(outputPath);

            ZipEntry entry = zipFile.getEntry(fileName);
            if (entry != null) {
                InputStream inputStream = zipFile.getInputStream(entry);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void writeTextToFile(String filePath, String text) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter writer = new FileWriter(file);
		writer.write(text);
		writer.close();
	}
	
	public static String getFileName(String filePath) {
        return new File(filePath).getName();
    }
    
	public static void setDialogCanClose(DialogInterface builder,boolean canClose) {
		try 
		{
			Field field = builder.getClass().getSuperclass().getDeclaredField("mShowing");
			field.setAccessible(true);
			field.set(builder, canClose);
		} catch (Exception e) {
			TLog.e(e);
		}
	}
}
