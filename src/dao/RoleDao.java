package dao;

import domain.Privilege;
import domain.Role;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.util.List;
import java.util.Set;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 10:44 2017/12/10
 * @Modified By:
 */
public class RoleDao {

    private QueryRunner runner = JdbcUtils.getQueryRunner();

    //添加角色进数据库
    public void add(Role role){
        try{
            String sql = "insert into role(id,name,description) values(?,?,?)";
            Object[] params = {role.getId(),role.getName(),role.getDescription()};
            runner.update(sql,params);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //根据id来查询角色
    public Role find(String id){
        try{
            //1.查找角色的基本信息
            String sql = "select * from role where id=?";
            Role role = (Role)runner.query(sql,new BeanHandler(Role.class),id);

            //2.找出角色的权限
            sql = "select p.* from role_privilege rp,privilege p where rp.role_id=? and p.id=rp.privilege_id";
            List<Privilege> list = (List<Privilege>)runner.query(sql,new BeanListHandler(Privilege.class),id);
            role.getPrivileges().addAll(list);
            return role;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    //获取所有角色
    public List getAll(){
        try{
            String sql = "select * from role";
            List<Role> list = (List<Role>)runner.query(sql,new BeanListHandler(Role.class));

            for(Role r:list){
                sql = "select p.* from role_privilege rp,privilege p where rp.role_id=? and p.id=rp.privilege_id";
                List<Privilege> listp = (List<Privilege>)runner.query(sql,new BeanListHandler(Privilege.class),r.getId());
                r.getPrivileges().addAll(listp);
            }
            return list;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    //更新角色的权限
    public void updatePrivilege(Role role,List<Privilege> privileges){
        try{
            //删除角色拥有的所有权限
            String sql = "delete from role_privilege where role_id=?";
            Object[] params = {role.getId()};
            JdbcUtils.addUpdateDelete(sql,params);

            //为角色赋予新的权限
            for(Privilege p:privileges){
                sql = "insert into role_privilege(role_id,privilege_id) values(?,?)";
                Object[] params1={role.getId(),p.getId()};
                JdbcUtils.addUpdateDelete(sql,params1);
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
