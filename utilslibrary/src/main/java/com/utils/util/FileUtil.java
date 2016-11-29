package com.utils.util;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;

public class FileUtil
{
	private static final String TAG = "FileUtil";
	private static String mStrSDCardPath = null;

	/**
	 * 判断sd卡是否正常挂载（是否有sd卡）
	 * @return
	 */
	public static boolean isSDCardReady()
	{
		// sd卡正常挂载
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			return true;
		}
		return false;
	}

	/**
	 * 读取sd卡路径
	 * @return
	 */
	public static String getSDCardPath()
	{
		if (StringUtil.isEmptyOrNull(mStrSDCardPath))
		{
			mStrSDCardPath = Environment.getExternalStorageDirectory().toString();
		}
		return mStrSDCardPath;
	}
	
	/**
	 * 创建文件夹
	 * @param folderName 文件夹 eg dmb/media
	 * @param fileName   保存的文件名称
	 * @return 返回的是 eg  /mnt/sdcard/dmb/media/fileName
	 */
	public static String createFile(String folderName,String fileName) 
	{
		String filePath = null;
		// 判断是否存在sd卡
		if (!isSDCardReady()) 
		{
			// 如果不存在,
			return filePath;
		} 
		else 
		{
			// 获取sd卡路径
			filePath = getSDCardPath();
			// 文件所在目录
			filePath += "/" + folderName;
			// 判断目录是否存在，不存在则创建该目录
			File file = new File(filePath);
			if (!file.exists())
			{
				file.mkdirs();
			}
			filePath += "/" + fileName;
		}
		return filePath;
	}
	/**
	 * 获取sd卡路径可用空间大小
	 * @param nTotalSize
	 * @return
	 */
	public static boolean getSDCardSize(int[] nTotalSize)
	{
		try
		{
			StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().toString());
			long nTotalBlock = statFs.getBlockCount();
			long nBlockSize = statFs.getBlockSize();

			nTotalSize[0] = ((int) (nTotalBlock * nBlockSize / 1024L / 1024L));
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public static boolean getSDCardAvailableSize(int[] nAvailSize)
	{
		try
		{
			StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().toString());
			long nTotalBlock = statFs.getAvailableBlocks();
			long nBlockSize = statFs.getBlockSize();

			nAvailSize[0] = ((int) (nTotalBlock * nBlockSize / 1024L / 1024L));
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 复制文件
	 * @param srcPath 被复制文件
	 * @param destPath 复制后的文件
	 */
	public static void copyFile(String srcPath, String destPath)
	{
		copyFile(new File(srcPath), new File(destPath));
	}

	/**
	 * 复制文件
	 * @param from 从from文件
	 * @param to 复制到to文件
	 * @return
	 */
	public static boolean copyFile(File from, File to)
	{
		boolean ret = true;
		InputStream is = null;
		OutputStream os = null;

		if (!from.exists())
		{
			return false;
		}

		try
		{
			is = new FileInputStream(from);
			os = new FileOutputStream(to);

			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = is.read(buf)) != -1)
			{
				os.write(buf, 0, len);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ret = false;
		}
		finally
		{
			if (os != null)
			{
				try
				{
					os.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				os = null;
			}

			if (is != null)
			{
				try
				{
					is.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				is = null;
			}
		}
		return ret;
	}

	/**
	 * 把数据写入文件中
	 * @param file 文件
	 * @param data 数据
	 * @return
	 */
	public static boolean writeFile(File file, byte[] data)
	{
		FileOutputStream fos = null;
		try
		{
			if (!file.getParentFile().exists())
			{
				file.getParentFile().mkdirs();
			}
			if (!file.exists())
			{
				file.createNewFile();
			}
			fos = new FileOutputStream(file);
			fos.write(data);
			fos.flush();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (fos != null)
			{
				try
				{
					fos.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 判断文件路劲是否存在
	 * @param pathStr
	 * @return
	 */
	public static boolean isExists(String pathStr)
	{
		if (StringUtil.isEmptyOrNull(pathStr))
		{
			return false;
		}
		return new File(pathStr).exists();
	}

	/**
	 * 创建指定文件
	 * @param pathStr
	 */
	public static void makeDir(String pathStr)
	{
		if (StringUtil.isEmptyOrNull(pathStr))
		{
			return;
		}

		File file = new File(pathStr);
		if (!file.exists())
		{
			file.mkdirs();
		}
	}

	/**
	 * 删除指定路劲的文件夹及以下文件
	 * @param pathStr
	 */
	public static void delDir(String pathStr)
	{
		if (StringUtil.isEmptyOrNull(pathStr))
		{
			return;
		}

		File dir = new File(pathStr);
		if (dir.exists())
		{
			File[] tmp = dir.listFiles();
			for (int i = 0; i < tmp.length; i++)
			{
				if (tmp[i].isDirectory())
				{
					delDir(pathStr + "/" + tmp[i].getName());
				}
				else
				{
					tmp[i].delete();
				}
			}
			dir.delete();
		}
	}

	/**
	 * 删除整个文件
	 * @param filePath
	 * @return
	 */
	public static boolean deleteFile(String filePath)
	{
		if (StringUtil.isEmptyOrNull(filePath))
		{
			return false;
		}

		File file = new File(filePath);
		if (file.exists())
		{
			return file.delete();
		}
		return false;
	}

	/**
	 * 重命名
	 * @param oldFile 旧文件名
	 * @param newFile 新文件
	 * @return
	 */
	public static boolean rename(File oldFile, File newFile)
	{
		if (!oldFile.exists())
		{
			return false;
		}
		if (!oldFile.renameTo(newFile))
		{
			return false;
		}
		return true;
	}

	// 判断文件夹下面是否有文件
	public static boolean isIDCardFileExist(String srcDir)
	{
		File file = new File(srcDir + "/1.jpg");
		if (file.exists())
		{
			return true;
		}
		file = new File(srcDir + "/2.jpg");
		if (file.exists())
		{
			return true;
		}
		return false;
	}

	public String getInputMessage() throws IOException
	{
		byte[] buffer = new byte[1024];
		int count = System.in.read(buffer);

		char[] ch = new char[count - 2];
		for (int i = 0; i < count - 2; i++)
		{
			ch[i] = ((char) buffer[i]);
		}
		String str = new String(ch);
		return str;
	}

	/**
	 * 获取文件夹大小
	 * @param file
	 * @return  bite
	 * @throws Exception
	 */
	public static long getFolderSize(File file) throws Exception {
		long size = 0;
		try {
			File[] fileList = file.listFiles();
			for (int i = 0; i < fileList.length; i++) {
				// 如果下面还有文件
				if (fileList[i].isDirectory()) {
					size = size + getFolderSize(fileList[i]);
				} else {
					size = size + fileList[i].length();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}


	/**
	 * 文件大小  单位转换之后
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String getCacheSize(File file) throws Exception {
		return getFormatSize(getFolderSize(file));
	}

	/**
	 * 格式化单位
	 *
	 * @param size
	 * @return
	 */
	public static String getFormatSize(double size) {
		double kiloByte = size / 1024;
		if (kiloByte < 1) {
			return size + "Byte";
		}

		double megaByte = kiloByte / 1024;
		if (megaByte < 1) {
			BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
			return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "KB";
		}

		double gigaByte = megaByte / 1024;
		if (gigaByte < 1) {
			BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
			return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "MB";
		}

		double teraBytes = gigaByte / 1024;
		if (teraBytes < 1) {
			BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
			return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "GB";
		}
		BigDecimal result4 = new BigDecimal(teraBytes);
		return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
				+ "TB";
	}
}