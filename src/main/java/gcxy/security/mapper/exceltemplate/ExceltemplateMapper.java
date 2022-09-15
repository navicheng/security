package gcxy.security.mapper.exceltemplate;

import gcxy.security.bean.Schapter;
import gcxy.security.bean.Scontent;
import gcxy.security.bean.Security;
import gcxy.security.bean.Sregulation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExceltemplateMapper {
    int insertSchapter(Schapter schapter);

    int insertSecurity(Security security);

    int insertScontent(Scontent scontent);

    int insertSregulation(Sregulation sregulation);

    //int deleteSecurityRepeatData();
    int deleteSecurityRepeatData(@Param("chapterId") String chapterId);

    //int deleteSchpaterRepeatData();
    int deleteSchpaterRepeatData(@Param("categoryId") String categoryId);


}
