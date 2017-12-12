package tag;

import domain.Privilege;
import domain.User;
import service.SecurityService;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 17:03 2017/12/11
 * @Modified By:
 */
public class PermissionTag extends SimpleTagSupport {

    private String value;

    public void setValue(String value){
        this.value=value;
    }

    @Override
    public void doTag() throws JspException,IOException {
        //判断用户拥有的权限中，是否包含value
        PageContext pageContext = (PageContext)this.getJspContext();
        HttpSession session = pageContext.getSession();
        User user = (User)session.getAttribute("user");

        if(user!=null){
            /*
             得到用户的所有权限，如果不想new这个业务对象的话，也行
             这时，用户登录进来了你就要把用户的所有权限找出来，即用户对象里面有一个集合维护他所有的权限
             但不想改动以前的设计，调用service去完成
             */
            SecurityService service = new SecurityService();
            List<Privilege> privileges = service.getUserAllPrivilege(user.getId());
            boolean b = false;
            for(Privilege p:privileges){
                if(p.getName().equals(value)){
                    b = true;
                    break;
                }
            }
            if(b){
                this.getJspBody().invoke(null);
            }
        }
    }
}
