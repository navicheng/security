package gcxy.security.service;

import gcxy.security.bean.Security;
import gcxy.security.mapper.security.SecurityMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Transactional
public class SecurityService {
    @Autowired(required = false)
    SecurityMapper securityMapper;

    /**
     * 返回安全管理表的所有内容
     * @return
     */
    public CopyOnWriteArrayList<Security> getAllSecurityRecord(){
        return securityMapper.getAllSecurityRecord();
    }

    /**
     * 返回指定类别的安全管理项
     * @param categoryId
     * @return
     */
    public CopyOnWriteArrayList<Security> getSecurityRecordByCategoryId(@Param("categoryId")int categoryId){
        return securityMapper.getSecurityRecordByCategoryId(categoryId);
    }

    /**
     * 返回指定项目类别的安全管理项目
     * @param typeId
     * @return
     */
    public CopyOnWriteArrayList<Security> getSecurityRecordByTypeId(@Param("chapterId") String chapterId,@Param("typeId")String typeId){
        return securityMapper.getSecurityRecordByTypeId(chapterId,typeId);
    }

    /**
     * 返回指定章节的安全管理项目
     * @param chapterId
     * @return
     */
    public CopyOnWriteArrayList<Security> getSecurityRecordByChapterId(@Param("chapterId") String chapterId){
        return securityMapper.getSecurityRecordByChapterId(chapterId);
    }
}
