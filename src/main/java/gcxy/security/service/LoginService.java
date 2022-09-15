package gcxy.security.service;

import gcxy.security.bean.User;
import gcxy.security.mapper.login.LoginMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginService {
    @Autowired(required = false)
    LoginMapper loginMapper;
    //返回指定id的密码
    public User getPasswordByUserId(@Param("userid") String userid) {
        return loginMapper.getPasswordByUserId(userid);
    }
    //判断是否含有用户id
    public  String containUserId(@Param("userid") String userid){
        return loginMapper.containUserId(userid);
    }

}
