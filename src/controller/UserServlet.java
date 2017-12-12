package controller;

import domain.Role;
import domain.User;
import service.SecurityService;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 11:42 2017/12/11
 * @Modified By:
 */
@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {

    private SecurityService service = new SecurityService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");

        if(method.equals("getAll")){
            getAll(request,response);
        }

        if(method.equals("addUI")){
            addUI(request,response);
        }

        if(method.equals("add")){
            add(request,response);
        }

        if(method.equals("forUpdateUserRoleUI")){
            forUpdateUserRoleUI(request,response);
        }

        if(method.equals("updateRole")){
            updateRole(request,response);
        }

        if(method.equals("login")){
            login(request,response);
        }

        if(method.equals("logout")){
            logout(request,response);
        }
    }

    private void getAll(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        List<User> list = service.getAllUser();
        request.setAttribute("list",list);
        request.getRequestDispatcher("/security/listuser.jsp").forward(request,response);
    }

    private void addUI(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        request.getRequestDispatcher("/security/adduser.jsp").forward(request,response);
    }

    private void add(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        try{
            User user = WebUtils.request2Bean(request,User.class);
            user.setId(UUID.randomUUID().toString());
            service.addUser(user);

            request.setAttribute("message","添加成功!!!");
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("message","添加失败!!!!");
        }
        request.getRequestDispatcher("/message.jsp").forward(request,response);
    }

    private void forUpdateUserRoleUI(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String userId = request.getParameter("id");
        User user = service.findUser(userId);

        List<Role> list = service.getAllRole();
        request.setAttribute("user",user);
        request.setAttribute("list",list);
        request.getRequestDispatcher("/security/updateUserRole.jsp").forward(request,response);
    }

    private void updateRole(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        try{
            String userId = request.getParameter("uid");
            String[] rids = request.getParameterValues("rid");
            service.updateUserRole(userId,rids);

            request.setAttribute("message","更新成功!!!");
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("message","更新失败!!!");
        }
        request.getRequestDispatcher("/message.jsp").forward(request,response);
    }

    private void login(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = (User)service.findUser(username,password);
        if(user == null){
            request.setAttribute("message","用户名或密码错误");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
            return;
        }
        request.getSession().setAttribute("user",user);
        response.sendRedirect("/JavaWeb_Manager/index.jsp");
    }

    private void logout(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        request.getSession().removeAttribute("user");
        response.sendRedirect("/JavaWeb_Manager/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
