package com.lssemail.garden.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2018-8-13.
 * @author
 */
public class URLUtil {

    static Logger logger = LoggerFactory.getLogger(URLUtil.class);

    public static InputStream getInputStream(String url) throws Exception{

        URL urlObj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5 * 1000);
        String status = connection.getHeaderField(null);
        InputStream is = null;
        if(status.startsWith("HTTP/1.1 200")){
            is = connection.getInputStream();
            // 得到文件大小
            int i = is.available();
        }
        return is;

    }

    public static byte[] getBytes(String url) throws Exception{

        InputStream is = getInputStream(url);
        byte[] data = readInputStream(is);

        return data;
    }

    public static File getFile(String path, String url) throws Exception {

        byte[] data = getBytes(url);
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(data);
        outputStream.close();
        return file;
    }

    private static byte[] readInputStream(InputStream inStream) throws Exception{

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len=inStream.read(buffer)) != -1){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }


}
