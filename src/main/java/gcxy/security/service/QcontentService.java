package gcxy.security.service;

import gcxy.security.bean.Qcontent;
import gcxy.security.mapper.qcontent.QcontentMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Transactional
public class QcontentService {
    @Autowired(required = false)
    QcontentMapper qcontentMapper;

    /**
     * 返回所有的质量检查项
     *
     * @return
     */
    public CopyOnWriteArrayList<Qcontent> getAllQcontent() {
        return qcontentMapper.getAllQcontent();
    }

    /**
     * 返回所有的examinationContentId
     *
     * @return
     */
    public CopyOnWriteArrayList<Integer> getAllExaminationContentId() {
        return qcontentMapper.getAllExaminationContentId();
    }

    /**
     * 返回指定内容id的检查项
     *
     * @param examinationContentId
     * @return
     */
    CopyOnWriteArrayList<Qcontent> getQcontentByExaminationContentId(@Param("examinationContentId") int examinationContentId) {
        return qcontentMapper.getQcontentByExaminationContentId(examinationContentId);
    }
}
