package gcxy.security.mapper.quality;

import gcxy.security.bean.Quality;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.concurrent.CopyOnWriteArrayList;

@Mapper
public interface QualityMapper {
    /**
     * 返回质量管理表的所有内容
     * @return
     */
    CopyOnWriteArrayList<Quality> getAllQualityRecord();

    /**
     * 返回指定类别的质量管理项
     * @param categoryId
     * @return
     */
    CopyOnWriteArrayList<Quality> getQualityRecordByCategoryId(@Param("categoryId")int categoryId);
}
