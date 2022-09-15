package gcxy.security.service;

import gcxy.security.bean.Schapter;
import gcxy.security.mapper.schapter.SchapterMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Transactional
public class SchapterService {
    @Autowired(required = false)
    SchapterMapper schapterMapper;

    /**
     * 返回所有的安全目录
     * @return
     */
    public CopyOnWriteArrayList<Schapter> getAllSecurityChapter(){
        return schapterMapper.getAllSecurityChapter();
    }

    /**
     * 返回指定类别的章节
     * @param categoryId
     * @return
     */
    public CopyOnWriteArrayList<Schapter> getSecurityByCategoryId(@Param("categoryId")String categoryId){
        return schapterMapper.getSecurityByCategoryId(categoryId);
    }

    /**
     * 根据chapterid查chapter名称
     * @param chapterId
     * @return
     */
    public String getSchapterNameByChapterId(@Param("chapterId ") String chapterId){
        return schapterMapper.getSchapterNameByChapterId(chapterId);
    }
}
