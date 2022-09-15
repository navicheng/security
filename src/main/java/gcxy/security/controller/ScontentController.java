package gcxy.security.controller;

import gcxy.security.service.ScontentService;
import gcxy.security.utils.ResInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scontent")
@Api(tags = "安全表检查内容管理")
public class ScontentController {
    @Autowired
    ScontentService scontentService;

    /**
     * 返回所有的安全检查项
     * @return
     */
    @GetMapping("/records")
    @ApiOperation(value = "安全表全部检查内容")
    public ResInfo getAllScontent(){
        return  ResInfo.success(scontentService.getAllScontent());
    }

    /**
     * 返回所有的examinationContentId
     * @return
     */
    @GetMapping("/examinationContentId")
    @ApiOperation(value = "安全表全部examinationID")
    public ResInfo getAllExaminationContentId(){
        return ResInfo.success(scontentService.getAllExaminationContentId());
    }

    /**
     * 返回指定内容id的检查项
     * @param examinationContentId
     * @return
     */
    @GetMapping("/category/records")
    @ApiOperation("根据examinationContentId查询指定安全表检查内容")
    public ResInfo getScontentByExaminationContentId(@Param("examinationContentId") String examinationContentId){
        if(scontentService.getAllExaminationContentId().contains(examinationContentId))
            return ResInfo.success(scontentService.getScontentByExaminationContentId(examinationContentId));
        return ResInfo.error_param("不含此examinationContentId");
    }
}
