package gcxy.security.service;

import gcxy.security.bean.Category;
import gcxy.security.mapper.category.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Transactional
public class CategoryService {
    @Autowired(required = false)
    CategoryMapper categoryMapper;

    /**
     * 返回所有大类 市政 地铁 建筑 铁路
     * @return
     */
    public CopyOnWriteArrayList<Category> getAllCategory(){
        return categoryMapper.getAllCategory();
    }

    /**
     * 获取所有的分类号
     * @return
     */
    public CopyOnWriteArrayList<Integer> getAllCategoryId(){
        return categoryMapper.getAllCategoryId();
    }
}
