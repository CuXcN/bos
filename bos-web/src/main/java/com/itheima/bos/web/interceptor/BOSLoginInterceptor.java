package com.itheima.bos.web.interceptor;


import com.itheima.bos.domain.User;
import com.itheima.bos.utils.BOSUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
;

public class BOSLoginInterceptor extends MethodFilterInterceptor {


    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {


        ActionProxy proxy = invocation.getProxy();
        String actionname = proxy.getActionName();
        String username = proxy.getNamespace();
        String url = actionname + username;
        User user = BOSUtils.getLoginUser();
        if (user == null) {
            //没登陆
            return "login";
        }
        return invocation.invoke();
    }
}
