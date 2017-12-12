package dao;

import domain.Role;
import domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.util.List;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 11:14 2017/12/10
 * @Modified By:
 */
public class UserDao {

    private QueryRunner runner = JdbcUtils.getQueryRunner();

    //添加用户进数据库
    public void add(User user){
        try{
            String sql = "insert into user(id,username,password,description) values(?,?,?,?)";
            Object[] params = {user.getId(),user.getUsername(),user.getPassword(),user.getDescription()};
            JdbcUtils.addUpdateDelete(sql,params);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //根据id查找用户
    public User find(String id){
        try{
            String sql = "select * from user where id=?";
            User user = (User)runner.query(sql,new BeanHandler(User.class),id);
            if(user==null){
                return null;
            }

            //找出用户拥有的所有角色
            sql = "select r.* from user_role ur,role r where ur.user_id=? and r.id=ur.role_id";
            List<Role> list = (List<Role>)runner.query(sql,new BeanListHandler(Role.class),id);
            user.getRoles().addAll(list);
            return user;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    //登录
    public User find(String username,String password){
        try{
            String sql = "select * from user where username=? and password=?";
            Object[] params = {username,password};
            User user = (User)runner.query(sql,new BeanHandler(User.class),params);
            if(user == null){
                return null;
            }

            //找出用户拥有的所有角色
            sql = "select r.* from user_role ur,role r where ur.user_id=? and r.id=ur.role_id";
            List<Role> list = (List<Role>)runner.query(sql,new BeanListHandler(Role.class),user.getId());
            user.getRoles().addAll(list);
            return user;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    //更新用户角色
    public void updateUserRoles(User user,List<Role> roles){
        try{
            //先删除用户所有的角色
            String sql = "delete from user_role where user_id=?";
            Object[] params = {user.getId()};
            JdbcUtils.addUpdateDelete(sql,params);

            //再为用户赋予新的角色
            for(Role r:roles){
                sql = "insert into user_role(user_id,role_id) values(?,?)";
                Object[] params1 =  {user.getId(),r.getId()};
                JdbcUtils.addUpdateDelete(sql,params1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<User> getAll(){
        try{
            String sql = "select * from user";
            List<User> list = (List<User>) runner.query(sql,new BeanListHandler(User.class));
            return list;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
