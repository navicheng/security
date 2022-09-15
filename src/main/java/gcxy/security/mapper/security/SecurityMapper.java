package gcxy.security.mapper.security;

import gcxy.security.bean.Security;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.concurrent.CopyOnWriteArrayList;

@Mapper
public interface SecurityMapper {
    /**
     * 返回安全管理表的所有内容
     * @return
     */
    CopyOnWriteArrayList<Security> getAllSecurityRecord();

    /**
     * 返回指定类别的安全管理项
     * @param categoryId
     * @return
     */
    CopyOnWriteArrayList<Security> getSecurityRecordByCategoryId(@Param("categoryId")int categoryId);

    /**
     * 返回指定项目类别的安全管理项目
     * @param typeId
     * @return
     */
    CopyOnWriteArrayList<Security> getSecurityRecordByTypeId(@Param("chapterId") String chapterId,@Param("typeId") String typeId);

    /**
     * 返回指定章节的安全管理项目
     * @param chapterId
     * @return
     */
    CopyOnWriteArrayList<Security> getSecurityRecordByChapterId(@Param("chapterId") String chapterId);

}
