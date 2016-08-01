package com.seeyon.apps.sms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class FileUtil {

	/**
	 * 获取文件夹下所有文件名
	 * @param path
	 * @return
	 */
	public static String[] getFileName(String path) {
		File file = new File(path);
		String[] fileName = file.list();
		return fileName;
	}
	
	/**
	 * 递归删除文件夹
	 * 
	 * @param file
	 */
	public void deleteFolder(File path) {
		if (!path.exists())
			return;
		if (path.isFile()) {
			path.delete();
			return;
		}
		File[] files = path.listFiles();
		for (int i = 0; i < files.length; i++) {
			deleteFolder(files[i]);
		}
		path.delete();
	}

	/***
	 * 创建文件夹
	 * 
	 * @param folder
	 *            文件夹名称
	 */
	public static void newfolder(String folder) {
		File file = new File(folder);
		if (!file.exists()) {
			file.mkdir();
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 */
	public void deleteFile(File file) {
		if (file.exists())
			file.delete();
	}

	/**
	 * 复制文件
	 * 
	 * @param oldFile
	 * @param newFile
	 */
	public void copyFile(String oldFile, String newFile) {
		FileInputStream input  = null;
		FileOutputStream output  = null;
		try {
			 input = new FileInputStream(oldFile); // 旧文件
			 output = new FileOutputStream(newFile);// 复制到的文件位置
			int in = input.read();
			while (in != -1) {
				output.write(in);
				in = input.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input!=null) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(output!=null){
				try {
					output.flush();
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
