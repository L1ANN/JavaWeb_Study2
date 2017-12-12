package utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 16:59 2017/12/10
 * @Modified By:
 */
public class WebUtils {

    public static <T> T request2Bean(HttpServletRequest request, Class<T> beanClass){
        try{
            T t = beanClass.newInstance();
            Map map = request.getParameterMap();

            BeanUtils.populate(t,map);
            return t;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
