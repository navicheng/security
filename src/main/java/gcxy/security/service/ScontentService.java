package gcxy.security.service;

import gcxy.security.bean.Scontent;
import gcxy.security.mapper.scontent.ScontentMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
@Transactional
public class ScontentService {
    @Autowired(required = false)
    ScontentMapper scontentMapper;

    /**
     * 返回所有的安全检查项
     * @return
     */
    public CopyOnWriteArrayList<Scontent> getAllScontent(){
        return  scontentMapper.getAllScontent();
    }

    /**
     * 返回所有的examinationContentId
     * @return
     */
    public CopyOnWriteArraySet<String> getAllExaminationContentId(){
        return scontentMapper.getAllExaminationContentId();
    }

    /**
     * 返回指定内容id的检查项
     * @param examinationContentId
     * @return
     */
    public CopyOnWriteArrayList<Scontent>getScontentByExaminationContentId(@Param("examinationContentId") String examinationContentId){
        return scontentMapper.getScontentByExaminationContentId(examinationContentId);
    }
}
