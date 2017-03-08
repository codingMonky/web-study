package com.diff.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
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
public class PageOne extends ActionSupport implements ServletRequestAware,ServletResponseAware {

    private static Logger logger = Logger.getLogger(PageOne.class);

    private HttpServletRequest request;
    private HttpServletResponse response;
    private String redirectUrl;
    private String path;
    private List<String> list;
    private String userName;
    private String password;


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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public String pageOne() throws MalformedURLException {
        logger.debug("开始打开pageOne页面");
        Cookie[] cookies = request.getCookies();
        if(cookies !=null) {
            for (Cookie cookie : cookies) {
                logger.debug(cookie.getValue());
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
        path = "pageOne";
        redirectUrl="http://www.a.com/sso/diff/pageOne.action";
        return  "login";
    }

    public String login() throws MalformedURLException {
        Map<String,String> map = new HashMap<String,String>();
        map.put("userName",userName);
        map.put("password",password);
        String result = CheckTool.doGet("http://www.x.com/sso/diff/checkLogin.action",map);
        if("success".equals(result)){
            list = new ArrayList<String>();
            list.add("http://www.a.com/sso/diff/pageOne/addCookie.action");
            list.add("http://www.b.com/sso/diff/pageTwo/addCookie.action");
            return SUCCESS;
        }
        path = "pageOne";
        redirectUrl="http://www.a.com/sso/diff/pageOne.action";
        return "login";

    }

    public String addCookie(){
        Cookie cookie = new Cookie("sso","cookie");
        cookie.setPath("/");
        response.addCookie(cookie);
        return  null;

    }



    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }
}
