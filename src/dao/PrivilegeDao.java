package dao;

import domain.Privilege;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.util.List;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 10:35 2017/12/10
 * @Modified By:
 */
public class PrivilegeDao {

    private QueryRunner runner = JdbcUtils.getQueryRunner();

    //添加权限进数据库
    public void add(Privilege p){
        try{
            String sql = "insert into privilege(id,name,description) values(?,?,?)";
            Object[] params = {p.getId(),p.getName(),p.getDescription()};
            JdbcUtils.addUpdateDelete(sql,params);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //根据id查找权限
    public Privilege find(String id){
        try{
            String sql = "select * from privilege where id=?";
            return (Privilege)runner.query(sql,new BeanHandler(Privilege.class),id);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    //获取所有权限
    public List getAll(){
        try{
            String sql = "select * from privilege";
            return (List<Privilege>)runner.query(sql,new BeanListHandler(Privilege.class));
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
