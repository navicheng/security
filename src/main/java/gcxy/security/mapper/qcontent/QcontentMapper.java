package gcxy.security.mapper.qcontent;

import gcxy.security.bean.Qcontent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.concurrent.CopyOnWriteArrayList;

@Mapper
public interface QcontentMapper {
    /**
     * 返回所有的质量检查项
     *
     * @return
     */
    CopyOnWriteArrayList<Qcontent> getAllQcontent();

    /**
     * 返回所有的ExaminationContentId
     *
     * @return
     */
    CopyOnWriteArrayList<Integer> getAllExaminationContentId();

    /**
     * 返回指定内容id的检查项
     * @param examinationContentId
     * @return
     */
    CopyOnWriteArrayList<Qcontent> getQcontentByExaminationContentId(@Param("examinationContentId")int examinationContentId);
}
