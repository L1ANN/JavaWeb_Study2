package dao;


import domain.Privilege;
import domain.Resource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 21:34 2017/11/27
 * @Modified By:
 */
public class ResourceDao {

    private QueryRunner query = null;

    //添加资源进数据库
    public void add(Resource r){
            String sql = "insert into resource(id,uri,description) values(?,?,?)";
            Object[] params = {r.getId(),r.getUri(),r.getDescription()};
            JdbcUtils.addUpdateDelete(sql,params);
    }

    //根据uri进行查找资源
    public Resource find(String uri){
        try{
            query = JdbcUtils.getQueryRunner();
            String sql = "select * from resource where uri=?";
            Resource r = (Resource)query.query(sql,new BeanHandler(Resource.class),uri);
            if(r == null){
                return null;
            }

            //得到控制资源的权限（涉及多表查询）
            sql = "select p.* from resource r,privilege p where r.uri=? and p.id=r.privilege_id";
            Privilege p = (Privilege)query.query(sql,new BeanHandler(Privilege.class),uri);
            r.setPrivilege(p);
            return r;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    //根据id进行查找资源
    public Resource findById(String id){
        try{
            query = JdbcUtils.getQueryRunner();
            String sql = "select * from resource where id=?";
            Resource r = (Resource)query.query(sql,new BeanHandler(Resource.class),id);

            if(r == null){
                return null;
            }

            //得到控制资源的权限（涉及多表查询）
            sql = "select p.* from resource r,privilege p where r.uri=? and p.id=r.privilege_id";
            Privilege p = (Privilege)query.query(sql,new BeanHandler(Privilege.class),id);
            r.setPrivilege(p);
            return r;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    //查找所有资源
    public List getAll(){
        try{
            query = JdbcUtils.getQueryRunner();
            String sql = "select * from resource";
            List<Resource> list = (List<Resource>)query.query(sql,new BeanListHandler(Resource.class));

            for(Resource r:list){
                //得到控制资源的权限（涉及多表查询）
                sql = "select p.* from resource r,privilege p where r.id=? and p.id=r.privilege_id";
                Privilege p = (Privilege)query.query(sql,new BeanHandler(Privilege.class),r.getId());
                r.setPrivilege(p);
            }
            return list;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    //更新权限
    public void updatePrivilege(Resource r,Privilege p){
        try{
            query = JdbcUtils.getQueryRunner();
            String sql = "update resource set privilege_id=? where id=?";
            Object[] params ={p.getId(),r.getId()};
            JdbcUtils.addUpdateDelete(sql,params);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
