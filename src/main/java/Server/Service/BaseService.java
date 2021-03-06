package Server.Service;

import Common.User;
import Server.Dao.BaseDao;
import Server.Utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


/**
 * @author charlottexiao
 */
public class BaseService {

    public User login(String id, String pwd) {
        if(id.equals("")||id==null||pwd.equals("")||pwd==null){
            return null;
        }
        SqlSession sqlSession = MybatisUtils.get();
        BaseDao mapper = sqlSession.getMapper(BaseDao.class);
        List<User> logins = mapper.select(id, pwd);
        sqlSession.close();
        if(logins.size()!=1||logins==null){
            return null;
        }else{
            return logins.get(0);
        }
    }
}
