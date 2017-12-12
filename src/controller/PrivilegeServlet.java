package controller;

import domain.Privilege;
import service.SecurityService;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 16:15 2017/12/10
 * @Modified By:
 */
public class PrivilegeServlet extends javax.servlet.http.HttpServlet {

    private SecurityService service = new SecurityService();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String method = request.getParameter("method");
        //请求派发
        if(method.equals("getAll")){
            getAll(request,response);
        }

        if(method.equals("addUI")){
            addUI(request,response);
        }

        if(method.equals("add")){
            add(request,response);
        }
    }

    //获取所有权限
    private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        List<Privilege> list = service.getAllPrivilege();
        request.setAttribute("list",list);
        request.getRequestDispatcher("/security/listprivilege.jsp").forward(request,response);
    }

    //用于跳转到添加权限页面
    private void addUI(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        request.getRequestDispatcher("/security/addprivilege.jsp").forward(request,response);
    }

    //添加权限
    private void add(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        try{
            Privilege p = WebUtils.request2Bean(request,Privilege.class);
            p.setId(UUID.randomUUID().toString());
            service.addPrivilege(p);

            request.setAttribute("message","添加成功！！");
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("message","添加失败！");
        }
        request.getRequestDispatcher("/message.jsp").forward(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
