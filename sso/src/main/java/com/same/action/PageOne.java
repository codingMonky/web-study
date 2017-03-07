package com.same.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;

/**
 * @author yinlei
 * @date 2017/2/15 21:31
 */
public class PageOne extends ActionSupport implements ServletRequestAware {

    private static Logger logger = Logger.getLogger(PageOne.class);

    private HttpServletRequest request;
    private String redirectUrl;

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String pageOne() throws MalformedURLException {
        logger.debug("开始打开pageOne页面");
        Cookie[] cookies = request.getCookies();
        if(cookies !=null) {
            for (Cookie cookie : cookies) {
                logger.debug(cookie.getValue());
                if ("sso".equals(cookie.getName())){
                    String result = CheckTool.doGet("http://check.x.com/sso/same/check.action",cookie.getName(),cookie.getValue());
                    if("success".equals(result))
                        return SUCCESS;
                }
            }
        }
        redirectUrl="http://demo1.x.com/sso/same/pageOne.action";
        return  "login";
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
}
