package gcxy.security.controller;

import gcxy.security.service.SchapterService;
import gcxy.security.utils.ResInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schapter")
@Api(tags = "安全表管理")
public class SchapterController {
    @Autowired
    SchapterService schapterService;

    /**
     * 返回所有的安全目录
     * @return
     */
    @GetMapping("/all")
    @ApiOperation(value = "所有安全表")
    public ResInfo getAllSecurityChapter(){
        if(schapterService.getAllSecurityChapter()==null)
            return ResInfo.error_database("数据库无数据");
        return ResInfo.success(schapterService.getAllSecurityChapter());
    }

    @GetMapping("/category/records")
    @ApiOperation(value = "根据chapterid查询检查表")
    public ResInfo getSecurityByCategoryId(@Param("categoryId")String categoryId){
        if(schapterService.getSecurityByCategoryId(categoryId).size()==0){
            return ResInfo.error_database("数据库中无指定类别的章节");
        }
        return ResInfo.success(schapterService.getSecurityByCategoryId(categoryId));
    }

    @GetMapping("/record")
    @ApiOperation(value = "根据chapterID查章节名")
    public ResInfo getSchapterNameByChapterId(@Param("chapterId") String chapterId){
        return ResInfo.success(schapterService.getSchapterNameByChapterId(chapterId));
    }

}
