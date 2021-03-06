package Server.Utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author charlottexiao
 */
public class MybatisUtils {
    public static SqlSessionFactory sqlSessionFactory;
    static{
        try {
            InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory= new SqlSessionFactoryBuilder().build(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static SqlSession get(){
        return  sqlSessionFactory.openSession(true);
    }

}
