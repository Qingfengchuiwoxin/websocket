package com.socket.web.controller;

import com.socket.web.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @Author: 清风一阵吹我心
 * @ProjectName: socket
 * @Package: com.socket.web
 * @ClassName: ChatController
 * @Description:
 * @CreateDate: 2018/11/9 11:04
 * @Version: 1.0
 **/
@RequestMapping("socket")
@Controller
public class ChatController {

    private final String USER_SESSION = "USER_SESSION";

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String goLogin() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();

        //登录操作,判断是否是已经登录的用户
        if (null != session.getAttribute(USER_SESSION)) {
            //清除旧的用户
            session.removeAttribute(USER_SESSION);
        }
        //新登陆,构建一个用户
        String id = UUID.randomUUID().toString();
        user.setId(id);
        //将用户放入session
        session.setAttribute(USER_SESSION, user);

        //将登录信息放入数据库,便于协查跟踪者
        System.out.println("新的用户" + user);
        return "redirect:main";
    }

    /**
     * 跳转到聊天页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String goMain(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (null == session.getAttribute(USER_SESSION)) {
            return "login";
        }
        return "main";
    }


}
