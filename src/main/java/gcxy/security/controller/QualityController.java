package gcxy.security.controller;

import gcxy.security.service.CategoryService;
import gcxy.security.service.QualityService;
import gcxy.security.utils.ResInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quality")
public class QualityController {
    @Autowired
    QualityService qualityService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/records")
    public ResInfo getAllQualityRecord(){
        return ResInfo.success(qualityService.getAllQualityRecord());
    }

    /**
     * 返回指定类别的质量管理项
     * @param categoryId
     * @return
     */
    @GetMapping("/category/records")
    public ResInfo getQualityRecordByCategoryId(@Param("categoryId")int categoryId){
        if (categoryService.getAllCategoryId().contains(categoryId))
            return ResInfo.success(qualityService.getQualityRecordByCategoryId(categoryId));
        return ResInfo.error_param("不含此分类");
    }
}
