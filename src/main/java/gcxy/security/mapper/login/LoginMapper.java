package gcxy.security.mapper.login;

import gcxy.security.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {

    User getPasswordByUserId(@Param("userid") String userid);

    String containUserId(@Param("userid") String userid);


}
