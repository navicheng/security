package gcxy.security.controller;

import gcxy.security.service.SregulationService;
import gcxy.security.utils.ResInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sregulation")
public class SregulationController {
    @Autowired
    SregulationService sregulationService;

    /**
     * 返回所有的安全表依据
     * @return
     */
    @GetMapping("/records")
    public ResInfo getAllSregulation(){
        return ResInfo.success(sregulationService.getAllSregulation());
    }

    /**
     * 返回指定的regulation_id对应的安全检查项依据
     * @param regulationId
     * @return
     */
    @GetMapping("/category/records")
    public ResInfo getSregulationByRegulationId(@Param("regulationId")int regulationId){
        return ResInfo.success(sregulationService.getSregulationByRegulationId(regulationId));
    }
}
