package gcxy.security.controller;

import gcxy.security.bean.Quality;
import gcxy.security.bean.Schapter;
import gcxy.security.bean.Security;
import gcxy.security.service.QualityService;
import gcxy.security.service.SchapterService;
import gcxy.security.service.SecurityService;
import gcxy.security.utils.ResInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 用户搜索相关
 */

@RestController
@Api(tags = "查询相关功能", description="查询")
public class SerchController {

    @Autowired
    SchapterService schapterService;

    @GetMapping("/serch")
    @ApiOperation(value = "查询检查表")
    public ResInfo serch(@Param("str") String str) {
        CopyOnWriteArrayList<Schapter> schapterList = schapterService.getAllSecurityChapter();
        if(str==null||str.trim()==null){
            return ResInfo.success(schapterService.getAllSecurityChapter());//没有输入 则返回所有记录
        }
        CopyOnWriteArrayList<Object> obj = new CopyOnWriteArrayList<>();
        for (int i = 0; i < schapterList.size(); i++) {
            if (schapterList.get(i).getChapterName().indexOf(str) != -1)
                obj.add(schapterList.get(i));
        }

        if (obj.size() == 0) {
            return ResInfo.error_database("数据库无记录");
        }
        return ResInfo.success(obj);
    }
}
