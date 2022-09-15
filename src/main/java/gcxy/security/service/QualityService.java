package gcxy.security.service;

import gcxy.security.bean.Quality;
import gcxy.security.mapper.quality.QualityMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Transactional
public class QualityService {
    @Autowired(required = false)
    QualityMapper qualityMapper;

    /**
     * 返回质量管理表的所有内容
     * @return
     */
    public CopyOnWriteArrayList<Quality> getAllQualityRecord(){
        return qualityMapper.getAllQualityRecord();
    }

    /**
     * 返回指定类别的安全管理项
     * @param categoryId
     * @return
     */
    public CopyOnWriteArrayList<Quality> getQualityRecordByCategoryId(@Param("categoryId")int categoryId){
        return qualityMapper.getQualityRecordByCategoryId(categoryId);
    }
}
