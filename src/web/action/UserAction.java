package web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import domain.User;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import service.UserService;

import java.io.IOException;
import java.util.List;

/**
 * @description: 用户action
 * @author: Will.Guo
 * @create: 2018-07-21 02:37
 **/
public class UserAction extends ActionSupport implements ModelDriven<User> {

    private User user = new User();
    @Override
    public User getModel() {
        return user;
    }

    private UserService userService ;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String regist() {
        userService.regist(user);
        return LOGIN;
    }

    public String login() {
        User existUser = userService.login(user);
        if (existUser == null) {
            this.addActionError("用户名或者密码错误！");
            return LOGIN;
        } else {
            ActionContext.getContext().getSession().put("existUser", existUser);
            return SUCCESS;
        }
    }

    public String findAllUser() throws IOException {
        List<User> list = userService.findAll();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"linkMans","baseDictSource","baseDictLevel","baseDictIndustry"});
        JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return NONE;
    }

}
