package thercn.ajide.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

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

	/*

	 public static void deCompressTarGzip(String targzFilePath, String targetDir) throws IOException {
	 //解压文件
	 Path source = Paths.get(targzFilePath);
	 //解压到哪
	 Path target = Paths.get(targetDir);

	 if (Files.notExists(source)) {
	 throw new IOException("您要解压的文件不存在");
	 }

	 //InputStream输入流，以下四个流将tar.gz读取到内存并操作
	 //BufferedInputStream缓冲输入流
	 //GzipCompressorInputStream解压输入流
	 //TarArchiveInputStream解tar包输入流
	 try (InputStream fi = Files.newInputStream(source);
	 BufferedInputStream bi = new BufferedInputStream(fi);
	 GzipCompressorInputStream gzi = new GzipCompressorInputStream(bi);
	 TarArchiveInputStream ti = new TarArchiveInputStream(gzi)) {
	 ArchiveEntry entry;
	 while ((entry = ti.getNextEntry()) != null) {

	 //获取解压文件目录，并判断文件是否损坏
	 Path newPath = zipSlipProtect(entry, target);

	 if (entry.isDirectory()) {
	 //创建解压文件目录
	 Files.createDirectories(newPath);
	 } else {
	 //再次校验解压文件目录是否存在
	 Path parent = newPath.getParent();
	 if (parent != null) {
	 if (Files.notExists(parent)) {
	 Files.createDirectories(parent);
	 }
	 }
	 // 将解压文件输入到TarArchiveInputStream，输出到磁盘newPath目录
	 Files.copy(ti, newPath, StandardCopyOption.REPLACE_EXISTING);

	 }
	 }
	 }

	 }

	 //判断压缩文件是否被损坏，并返回该文件的解压目录
	 private static Path zipSlipProtect(ArchiveEntry entry, Path targetDir)
	 throws IOException {

	 Path targetDirResolved = targetDir.resolve(entry.getName());
	 Path normalizePath = targetDirResolved.normalize();

	 if (!normalizePath.startsWith(targetDir)) {
	 throw new IOException("压缩文件已被损坏: " + entry.getName());
	 }

	 return normalizePath;
	 }*/


}
