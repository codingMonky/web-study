package com.same.action;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author yinlei
 * @date 2017/3/3 1:04
 */
public class CheckTool {

    private static Logger logger = LoggerFactory.getLogger(CheckTool.class);

    public static String doGet(String url, String cookieName, String cookieValue) throws MalformedURLException {
        logger.debug("开始验证Cookie");
        StringBuffer sb = new StringBuffer();
        HttpURLConnection httpURLConnection = null;
        InputStream in = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            url = url + "?cookieName=" + cookieName + "&cookieValue=" + cookieValue;
            URL urls = new URL(url);
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
