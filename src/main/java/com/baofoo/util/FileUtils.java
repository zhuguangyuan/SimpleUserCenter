package com.baofoo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author：CHQ
 * @Description： 文件相关操作 上传、删除
 * @Date: Created in 2018/6/5 18:56
 * @Modified by:
 */
public class FileUtils {

    /**
     * 删除文件
     *
     * @param path     路径
     * @param filename 文件名
     * @return
     */
    public static boolean fileDel(String path, String filename) {
        boolean flag = false;
        try {
            File fileTemp = new File(path + filename);
            if (fileTemp.exists()) {
                fileTemp.delete();
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean fileDel(String FilePathName) {
        boolean flag = false;
        try {
            File fileTemp = new File(FilePathName);
            if (fileTemp.exists()) {
                fileTemp.delete();
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 读取文件 返回byte[]
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] getContent(String filePath) throws IOException {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            System.out.println("file too big...");
            return null;
        }
        FileInputStream FiIs = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        int offset = 0;
        int numRead = 0;
        while (offset < buffer.length
                && (numRead = FiIs.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }
        FiIs.close();
        // 确保所有数据均被读取
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }        
        return buffer;
    }

    public static boolean fileIsexist(String path) {
        File file = new File(path);
        if (file.exists()) {
            return true;
        }
        return false;
    }
}
