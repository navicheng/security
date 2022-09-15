package gcxy.security.mapper.user;

import gcxy.security.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.concurrent.CopyOnWriteArrayList;

@Mapper
public interface UserMapper {

    /**
     * 返回指定userid的用户信息
     * @param userid
     * @return
     */
    User getUserByUserId(@Param("userid") String userid);

}
