package gcxy.security.mapper.schapter;

import gcxy.security.bean.Schapter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.concurrent.CopyOnWriteArrayList;

@Mapper
public interface SchapterMapper {
    /**
     * 返回所有的安全目录
     *
     * @return
     */
    CopyOnWriteArrayList<Schapter> getAllSecurityChapter();

    /**
     * 返回指定分类的章节
     * @param categoryId
     * @return
     */
    CopyOnWriteArrayList<Schapter> getSecurityByCategoryId(@Param("categoryId") String categoryId);

    /**
     * 根据chapterid查chapter名称
     * @param chapterId
     * @return
     */
    String getSchapterNameByChapterId(@Param("chapterId") String chapterId);
}
