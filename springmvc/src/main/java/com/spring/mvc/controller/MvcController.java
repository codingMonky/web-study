package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author yinlei
 * date   2017/3/16 21:04
 */
@Controller
public class MvcController {

    @RequestMapping("/message")
    @ResponseBody
    public String getMessage() {
        return "abcd";
    }

    @RequestMapping("/page")
    public String getPage() {

        return "page";
    }
}
