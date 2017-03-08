package com.diff.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yinlei
 * @date 2017/2/15 21:31
 */
public class PageTwo extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private static Logger logger = LoggerFactory.getLogger(PageTwo.class);

    private HttpServletRequest request;
    private HttpServletResponse response;
    private String redirectUrl;
    private String userName;
    private String password;
    private String path;
    private List<String> list;


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

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

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String pageTwo() throws MalformedURLException {
        logger.info("开始打开pageTwo页面");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sso".equals(cookie.getName())){
                    Map<String,String> map = new HashMap();
                    map.put("cookieName",cookie.getName());
                    map.put("cookieValue",cookie.getValue());
                    String result = CheckTool.doGet("http://www.x.com/sso/diff/check.action",map);
                    if("success".equals(result))
                        return SUCCESS;
                }
            }
        }
        path = "pageTwo";
        redirectUrl = "http://demo2.x.com/sso/same/pageTwo.action";
        return "login";
    }

    public String login() throws MalformedURLException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userName", userName);
        map.put("password", password);
        String result = CheckTool.doGet("http://www.x.com/sso/diff/checkLogin.action", map);
        if ("success".equals(result)) {
            list = new ArrayList<String>();
            list.add("http://www.a.com/sso/diff/pageOne/addCookie.action");
            list.add("http://www.b.com/sso/diff/pageTwo/addCookie.action");
            return SUCCESS;
        }
        path = "pageTwo";
        redirectUrl="http://www.a.com/sso/diff/pageOne.action";
        return LOGIN;
    }

    public String addCookie() {
        Cookie cookie = new Cookie("sso", "cookie");
        cookie.setPath("/");
        response.addCookie(cookie);
        return null;

    }


    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }
}
