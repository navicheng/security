package gcxy.security.controller;

import gcxy.security.service.CategoryService;
import gcxy.security.service.SecurityService;
import gcxy.security.utils.ResInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
@Api(tags = "安全表检查项管理")
public class SecurityController {
    @Autowired
    SecurityService securityService;
    @Autowired
    CategoryService categoryService;

    /**
     * 返回安全管理表的所有内容
     *
     * @return
     */
    @GetMapping("/records")
    @ApiOperation(value = "全部检查项")
    public ResInfo getAllSecurityRecord() {
        return ResInfo.success(securityService.getAllSecurityRecord());
    }

    /**
     * 返回指定类别的安全管理项
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/category/records")
    @ApiOperation(value = "指定类别的安全表检查项")
    public ResInfo getSecurityRecordByCategoryId(@Param("categoryId") int categoryId) {
        if (categoryService.getAllCategoryId().contains(categoryId))
            return ResInfo.success(securityService.getSecurityRecordByCategoryId(categoryId));
        return ResInfo.error_param("不含此分类");

    }

    /**
     * 返回指定项目类别的安全管理项目
     *
     * @param typeId
     * @return
     */
    @GetMapping("/type/records")
    @ApiOperation(value = "指定项目类别和安全表名的检查项")
    public ResInfo getSecurityRecordByTypeId(@Param("chapterId") String chapterId,@Param("typeId") String typeId) {
        if (securityService.getSecurityRecordByTypeId(chapterId,typeId).size() == 0) {
            return ResInfo.error_database("数据库中该项目类型为空");
        }
        return ResInfo.success(securityService.getSecurityRecordByTypeId(chapterId,typeId));
    }

    /**
     * 返回指定章节的安全管理项目
     *
     * @param chapterId
     * @return
     */
    @GetMapping("/chapter/records")
    @ApiOperation(value = "指定安全表的检查项")
    public ResInfo getSecurityRecordByChapterId(@Param("chapterId") String chapterId) {
        if (securityService.getSecurityRecordByChapterId(chapterId).size() == 0) {
            return ResInfo.error_database("数据库中该项目类型为空");
        }
        return ResInfo.success(securityService.getSecurityRecordByChapterId(chapterId));
    }
}
