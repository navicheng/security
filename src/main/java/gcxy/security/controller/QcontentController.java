package gcxy.security.controller;

import gcxy.security.service.QcontentService;
import gcxy.security.utils.ResInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qcontent")

public class QcontentController {
    @Autowired
    QcontentService qcontentService;

    /**
     * 返回所有的质量检查项
     *
     * @return
     */
    @GetMapping("/records")

    public ResInfo getAllQcontent() {
        return ResInfo.success(qcontentService.getAllQcontent());
    }

    /**
     * 返回所有的examinationContentId
     *
     * @return
     */
    @GetMapping("/examinationContentId")

    public ResInfo getAllExaminationContentId() {
        return ResInfo.success(qcontentService.getAllExaminationContentId());
    }

    /**
     * 返回指定内容id的检查项
     *
     * @param examinationContentId
     * @return
     */
    @GetMapping("/category/records")

    public ResInfo getQcontentByExaminationContentId(@Param("examinationContentId") int examinationContentId) {
        if (qcontentService.getAllExaminationContentId().contains(examinationContentId))
            return ResInfo.success(qcontentService.getAllQcontent());
        return ResInfo.error_param("不含此examinationContentId");
    }
}
