package gcxy.security.mapper.scontent;

import gcxy.security.bean.Scontent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

@Mapper
public interface ScontentMapper {
    /**
     * 返回所有的安全检查项
     * @return
     */
    CopyOnWriteArrayList<Scontent> getAllScontent();

    /**
     * 返回所有的ExaminationContentId
      * @return
     */
    CopyOnWriteArraySet<String> getAllExaminationContentId();

    /**
     * 返回指定内容id的检查项
     * @param examinationContentId
     * @return
     */
    CopyOnWriteArrayList<Scontent> getScontentByExaminationContentId(@Param("examinationContentId")String examinationContentId);
}
