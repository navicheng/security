package gcxy.security.controller;

import gcxy.security.service.LoginService;
import gcxy.security.utils.ResInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Duration;

@RestController
@Api(tags = "登录管理")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public ResInfo getPasswordByUserId(@Param("userid") String userid, @Param("password") String password,HttpServletRequest request) {
        HttpSession session=request.getSession();
        session.setMaxInactiveInterval(60*5);
        if(session.getAttribute(userid)==null) {
            session.setAttribute(userid, 1);
            if (loginService.containUserId(userid) == null) {
                return ResInfo.error_normal("不含该用户");
            } else {
                if (!loginService.getPasswordByUserId(userid).password.equals(password)) {
                    return ResInfo.error_normal("密码错误");
                }
            }
            return ResInfo.success(loginService.getPasswordByUserId(userid));
        }
        else{
            return ResInfo.error_normal("不能重复登录");
        }
    }

}
