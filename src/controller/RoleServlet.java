package controller;

import domain.Privilege;
import domain.Role;
import service.SecurityService;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 10:46 2017/12/11
 * @Modified By:
 */
public class RoleServlet extends HttpServlet {

    private SecurityService service = new SecurityService();

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
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

        if(method.equals("forUpdateRolePrivilegeUI")){
            forUpdateRolePrivilegeUI(request,response);
        }

        if(method.equals("updatePrivilege")){
            updatePrivilege(request,response);
        }
    }

    //获取所有角色
    private void getAll(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        List<Role> list = service.getAllRole();
        request.setAttribute("list",list);
        request.getRequestDispatcher("/security/listrole.jsp").forward(request,response);
    }

    private void addUI(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        request.getRequestDispatcher("/security/addrole.jsp").forward(request,response);
    }

    private void add(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        try{
            Role role = WebUtils.request2Bean(request,Role.class);
            role.setId(UUID.randomUUID().toString());
            service.addRole(role);

            request.setAttribute("message","添加成功!!!");
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("message","添加失败!!!");
        }
        request.getRequestDispatcher("/message.jsp").forward(request,response);
    }

    private void forUpdateRolePrivilegeUI(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String roleId = request.getParameter("id");
        Role r = service.findRole(roleId);

        List<Privilege> list = service.getAllPrivilege();
        request.setAttribute("role",r);
        request.setAttribute("list",list);
        request.getRequestDispatcher("/security/updateRolePrivilege.jsp").forward(request,response);
    }

    private void updatePrivilege(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        try{
            String roleid = request.getParameter("rid");
            String[] pids = request.getParameterValues("pid");
            service.updateRolePrivileges(roleid,pids);

            request.setAttribute("message","更新成功!!!");
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("message","更新失败!!!");
        }
        request.getRequestDispatcher("/message.jsp").forward(request,response);
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        doPost(request,response);
    }
}
