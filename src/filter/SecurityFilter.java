package filter;

import domain.Privilege;
import domain.Resource;
import domain.User;
import service.SecurityService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 12:36 2017/12/11
 * @Modified By:
 */
@WebFilter(filterName = "SecurityFilter")
public class SecurityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        //1.检查用户是否已经登录
        User user = (User)request.getSession().getAttribute("user");

        //2.没登录，返回登录页面
        if(user==null){
            request.setAttribute("message","请先登录！！！");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
            return;
        }

        //3.得到用户想访问的资源
        String uri = request.getRequestURI();

        //4.得到访问该资源需要的权限
        SecurityService service = new SecurityService();
        Resource r = service.findResource(uri);

        /*如果资源为null，也就是访问的资源不受权限系统控制，放行*/
        if(r == null){
            chain.doFilter(request,response);
            return;
        }
        Privilege required_Privilege = r.getPrivilege();//得到访问资源需要的权限

        //5.判断用户是否有相应的权限
        List<Privilege> list = service.getUserAllPrivilege(user.getId());//得到用户所有权限
        if(!list.contains(required_Privilege)){
            //6.没有权限，提示用户权限不足，联系管理员
            request.setAttribute("message","对不起，你没有权限，请联系管理员！！！");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }

        //7.如果有权限，则放行
        chain.doFilter(request,response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
