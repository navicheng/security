package gcxy.security.controller;

import gcxy.security.service.QregulationService;
import gcxy.security.utils.ResInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qregulation")
public class QregulationController {
    @Autowired
    QregulationService qregulationService;

    /**
     * 返回所有的质量表依据
     *
     * @return
     */
    @GetMapping("/records")
    public ResInfo getAllQregulation() {
        return ResInfo.success(qregulationService.getAllQregulation());
    }

    /**
     * 返回指定的regulation_id对应的质量检查项依据
     *
     * @param regulationId
     * @return
     */
    @GetMapping("/category/records")
    public ResInfo getQregulationByRegulationId(@Param("regulationId") int regulationId) {
        return ResInfo.success(qregulationService.getQregulationByRegulationId(regulationId));
    }

}
