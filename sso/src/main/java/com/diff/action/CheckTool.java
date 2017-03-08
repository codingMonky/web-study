package com.diff.action;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * @author yinlei
 * @date 2017/3/3 1:04
 */
public class CheckTool {

    private static Logger logger = LoggerFactory.getLogger(CheckTool.class);

    public static String doGet(String url, Map<String,String> map) throws MalformedURLException {
        logger.debug("开始验证Cookie");
        StringBuffer sb = new StringBuffer();
        HttpURLConnection httpURLConnection = null;
        InputStream in = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            StringBuffer sf_temp = new StringBuffer("?");
            int index = 0;
            for(Map.Entry<String,String> entry : map.entrySet()){
                sf_temp.append((index == 0? "":"&")).append(entry.getKey()).append("=").append(entry.getValue());
                index++;
            }
            URL urls = new URL(url+sf_temp.toString());
            httpURLConnection = (HttpURLConnection) urls.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            in = httpURLConnection.getInputStream();
            isr = new InputStreamReader(in);
            br = new BufferedReader(isr);
            String temp = "";
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            br.close();
            isr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        logger.debug("获得验证结果"+sb.toString());
        return sb.toString();
    }
}
