package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;


/**
 * @Author:L1ANN
 * @Description: JDBC的工具类
 * @Date:Created in 21:23 2017/11/27
 * @Modified By:
 */
public class JdbcUtils {
    //初始化c3p0
    private static ComboPooledDataSource ds = null;
    static{
        //自动加载src目录下的c3p0的配置文件
        ds = new ComboPooledDataSource();
    }

    public static QueryRunner getQueryRunner(){
        QueryRunner query = new QueryRunner(ds);
        return query;
    }

    public static void addUpdateDelete(String sql,Object[] params){
        QueryRunner query = getQueryRunner();
        try{
            query.update(sql,params);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
