package com.sso.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


/**
 * @author yinlei
 * @date 2017/2/9 0:41
 */
public class UserAction extends ActionSupport implements ServletResponseAware {


    private String password;// 用户密码
    private String userName;//用户名
    private String redirectUrl;//重定向路径

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    private HttpServletResponse response;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String hello() {
        if ("abc".equals(userName) && "123".equals(password)) {
           Cookie cookie = new Cookie("sso","cookie");
            response.addCookie(cookie);
            return SUCCESS;
        }
        return "error";
    }

    public String home(){
        return SUCCESS;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response =  httpServletResponse;
    }
}
