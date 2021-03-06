package Server.Dao;


import Common.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author charlottexiao
 */
public interface BaseDao {
    @Select("select id,name from user where id = #{id} and pwd = #{pwd}")
    public List<User> select(@Param("id")String id, @Param("pwd") String pwd);
}
