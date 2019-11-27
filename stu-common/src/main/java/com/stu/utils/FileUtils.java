package com.stu.utils;

import java.io.*;

/**
 * 文件操作类
 */
public class FileUtils {

    /**
     * 输入流转成File
     * @param ins 输入流
     * @param file File
     */
    public static void inputStreamToFile(InputStream ins, File file){
        try{
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 输入流转成File
     * @param ins 输入流
     * @return File
     */
    public static File inputStreamToFile(InputStream ins){
        try{
            File file = File.createTempFile("temp", ".xls");
            inputStreamToFile(ins, file);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
