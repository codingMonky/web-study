package com.same.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author yinlei
 * @date 2017/2/9 0:41
 */
public class UserAction extends ActionSupport implements ServletResponseAware {

    private static Logger logger = LoggerFactory.getLogger(UserAction.class);

    private String password;// 用户密码
    private String userName;//用户名
    private String redirectUrl;//重定向路径

    private String cookieName;
    private String cookieValue;


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

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getCookieValue() {
        return cookieValue;
    }

    public void setCookieValue(String cookieValue) {
        this.cookieValue = cookieValue;
    }

    public String hello() {
        logger.debug("开始登陆");
        if ("abc".equals(userName) && "123".equals(password)) {
           Cookie cookie = new Cookie("sso","cookie");
           cookie.setDomain(".x.com");
           cookie.setPath("/");
            response.addCookie(cookie);
            logger.info("登陆成功");
            return SUCCESS;
        }
        logger.debug("登陆失败");
        return "error";
    }

    public void checkCookie() throws IOException {
        logger.debug("开始校验Cookie");
        String result = "fail";
        if("sso".equals(cookieName)&&"cookie".equals(cookieValue)){
           result = "success";
        }
        response.getWriter().print(result);
        response.getWriter().close();
    }


    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response =  httpServletResponse;
    }
}
