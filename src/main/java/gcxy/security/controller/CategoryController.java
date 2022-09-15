package gcxy.security.controller;

import gcxy.security.service.CategoryService;
import gcxy.security.utils.ResInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@Api(tags = "类别管理")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * 返回所有分类 市政 地铁 建筑 铁路
     *
     * @return
     */
    @GetMapping("/")
    @ApiOperation(value = "所有类别")
    public ResInfo getAllCategory() {
        return ResInfo.success(categoryService.getAllCategory());
    }

    /**
     * 获取所有的分类号
     *
     * @return
     */
    @GetMapping("/id")
    @ApiOperation(value = "获取所有的分类号")
    public ResInfo getAllCategoryId() {
        if (categoryService.getAllCategoryId().size() == 0) {
            return ResInfo.error_normal("当前无任何分类");
        }
        return ResInfo.success(categoryService.getAllCategoryId());
    }
}
