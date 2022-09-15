package gcxy.security.mapper.category;

import gcxy.security.bean.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.concurrent.CopyOnWriteArrayList;

@Mapper
public interface CategoryMapper {
    /**
     * 获取所有类别
     * @return
     */
    CopyOnWriteArrayList<Category> getAllCategory();

    /**
     * 获取所有的分类号
     * @return
     */
    CopyOnWriteArrayList<Integer> getAllCategoryId();
}
