package thercn.ajide.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.Toast;
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
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import thercn.ajide.IDEApplication;
import thercn.ajide.activities.ProjectActivity;

public class APPUtils {

	private static ProjectActivity mact;
	private static boolean isInitDone;
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
	/**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
	
	public static void setMainActivity(ProjectActivity act) {
		mact = act;
	}
	
	public static ProjectActivity getMainActivity() {
		return mact;
	}
	public static boolean checkSha1(String filePath, String sha1Value) {
        try {
            // 创建SHA-1摘要实例
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 使用文件输入流读取文件
            try (FileInputStream fis = new FileInputStream(filePath)) {
                byte[] dataBytes = new byte[1024];
                int nread = 0;
                while ((nread = fis.read(dataBytes)) != -1) {
                    md.update(dataBytes, 0, nread);
                }
            }
            // 计算文件的SHA-1哈希值
            byte[] digest = md.digest();
            // 将哈希值转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            // 比较计算出的哈希值与给定的哈希值
            return sb.toString().equals(sha1Value);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
            return false;
        }
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

	public static boolean isInitDone() {
		return isInitDone;
	}
	
	public static void setIniIsDone(boolean z) {
		isInitDone = z;
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

	public static void print(Object text) {
		Toast.makeText(IDEApplication.context, text.toString(), Toast.LENGTH_SHORT).show();
	}
	
	public static String getFileName(String filePath) {
        return new File(filePath).getName();
    }

	public static List<String> getAllFile(final String directory,final String suffix) throws IOException {

		final List<String> files = new ArrayList<>();
		Files.walkFileTree(Paths.get(directory), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (suffix != null) {
						if (file.toString().endsWith(suffix)) {
							files.add(file.toString());
						}
					} else {
						files.add(file.toString());
					}
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
		return files;
	}
	

}
