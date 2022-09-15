package gcxy.security.service;

import gcxy.security.bean.User;
import gcxy.security.mapper.user.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Transactional
public class UserService {
    @Autowired(required = false)
    UserMapper userMapper;
    /**
     * 返回指定userid的用户信息
     * @param userid
     * @return
     */
    public User getUserByUserId(@Param("userid") String userid){
        return userMapper.getUserByUserId(userid);
    }
}
