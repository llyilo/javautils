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
    
        /**
	 * 文件重命名
	 * @param path
	 * @param oldname
	 * @param newname
	 */
	public void renameFile(String path,String oldname,String newname){ 
        if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名 
            File oldfile=new File(path+"/"+oldname); 
            File newfile=new File(path+"/"+newname); 
            if(!oldfile.exists()){
                return;//重命名文件不存在
            }
            if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
                System.out.println(newname+"已经存在！"); 
            else{ 
                oldfile.renameTo(newfile); 
            } 
        }else{
            System.out.println("新文件名和旧文件名相同...");
        }
    }
}
