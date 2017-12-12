package controller;

import domain.Privilege;
import domain.Resource;
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
 * @Date:Created in 18:39 2017/12/10
 * @Modified By:
 */
@WebServlet(name = "ResourceServlet")
public class ResourceServlet extends HttpServlet {

    private SecurityService service = new SecurityService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");

        if (method.equals("getAll")) {
            getAll(request, response);
        }

        if (method.equals("addUI")) {
            addUI(request, response);
        }

        if (method.equals("add")) {
            add(request, response);
        }

        if(method.equals("forUpdatePrivilegeUI")){
            forUpdatePrivilegeUI(request,response);
        }

        if(method.equals("updatePrivilege")){
            updatePrivilege(request,response);
        }
    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Resource> list = service.getAllRecource();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/security/listresource.jsp").forward(request, response);
    }

    private void addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/security/addresource.jsp").forward(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Resource r = WebUtils.request2Bean(request, Resource.class);
            r.setId(UUID.randomUUID().toString());
            service.addResource(r);

            request.setAttribute("message", "添加成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message","添加失败！！！");
        }

        request.getRequestDispatcher("/message.jsp").forward(request,response);
    }

    private void forUpdatePrivilegeUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resourceId = request.getParameter("id");
        Resource r = service.findResourceById(resourceId);

        //得到系统中的所有权限
        List<Privilege> list = service.getAllPrivilege();

        request.setAttribute("resource",r);
        request.setAttribute("list",list);

        request.getRequestDispatcher("/security/updateResourcePrivilege.jsp").forward(request, response);
    }

    private void updatePrivilege(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        try{
            String resourceId = request.getParameter("rid");
            String privilegeId = request.getParameter("pid");
            service.updateResourcePrivilege(resourceId,privilegeId);

            request.setAttribute("message","更新成功！！！");
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("message","更新失败！！！");
        }
        request.getRequestDispatcher("/message.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
