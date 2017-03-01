package com.sso.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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

    public String pageOne(){
        Cookie[] cookies = request.getCookies();
        redirectUrl ="/sso/page/pageOne.jsp";
        for(Cookie cookie:cookies){
            logger.debug(cookie.getValue());
            if("sso".equals(cookie.getName())&&"cookie".equals(cookie.getValue()))
                return SUCCESS;
        }
        return  "login";
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
}
