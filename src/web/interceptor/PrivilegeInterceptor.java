package web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import domain.User;
import org.apache.struts2.ServletActionContext;

/**
 * @description:
 * @author: Will.Guo
 * @create: 2018-07-22 20:50
 **/
public class PrivilegeInterceptor extends MethodFilterInterceptor {

    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
        if (existUser == null) {
            ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();
            actionSupport.addActionError("你没有登录，没有相关权限");
            return ActionSupport.LOGIN;
        }
        return actionInvocation.invoke();
    }
}
