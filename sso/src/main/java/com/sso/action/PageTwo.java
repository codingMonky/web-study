package com.sso.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author yinlei
 * @date 2017/2/15 21:31
 */
public class PageTwo extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest request;
    private String redirectUrl;

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String pageTwo(){
        Cookie[] cookies = request.getCookies();
        redirectUrl ="/sso/page/pageTwo.jsp";
        for(Cookie cookie:cookies){

            if("sso".equals(cookie.getName())&&"cookie".equals(cookie.getValue()))
                return SUCCESS;
        }
        return  "login";
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
}
