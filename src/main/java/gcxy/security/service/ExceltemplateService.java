package gcxy.security.service;

import gcxy.security.bean.Schapter;
import gcxy.security.bean.Scontent;
import gcxy.security.bean.Security;
import gcxy.security.bean.Sregulation;
import gcxy.security.mapper.exceltemplate.ExceltemplateMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExceltemplateService {
    @Autowired(required = false)
    ExceltemplateMapper exceltemplateMapper;

    public int insertSchapter(Schapter schapter) {
        return exceltemplateMapper.insertSchapter(schapter);
    }

    public int insertSecurity(Security security) {
        return exceltemplateMapper.insertSecurity(security);
    }

    public int insertScontent(Scontent scontent) {
        return exceltemplateMapper.insertScontent(scontent);
    }

    public int insertSregulation(Sregulation sregulation){return exceltemplateMapper.insertSregulation(sregulation);}

    /*public int  deleteSecurityRepeatData(){
        return exceltemplateMapper.deleteSecurityRepeatData();
    }*/
    public int  deleteSecurityRepeatData(@Param("chapterId") String chapterId){
        return exceltemplateMapper.deleteSecurityRepeatData(chapterId);
    }

   /* public int deleteSchpaterRepeatData(){
        return exceltemplateMapper.deleteSchpaterRepeatData();
    }*/
    public int deleteSchpaterRepeatData(@Param("categoryId") String categoryId){
        return exceltemplateMapper.deleteSchpaterRepeatData(categoryId);
    }
}
