package gcxy.security.controller;

import gcxy.security.bean.*;
import gcxy.security.service.ExceltemplateService;
import gcxy.security.utils.PoiUtils;
import gcxy.security.utils.ResInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/excel")
@Api(tags = "数据管理")
public class ExceltemplateController {
    @Autowired
    ExceltemplateService exceltemplateService;

    @PostMapping("/import")
    @ApiOperation(value = "EXCEL数据导入")
    public ResInfo excelToDatabase(@Param("path") String path) throws IOException {
        String path1 = "D:\\安全检查表\\市政检查表\\"+ path+".xlsx";
        CopyOnWriteArrayList<Exceltemplate> obj = PoiUtils.poi(path1);
        Schapter schapter = new Schapter();
        Security security = new Security();
        Scontent scontent = new Scontent();
        Sregulation sregulation=new Sregulation();
        for (int i = 0; i < obj.size(); i++) {
            schapter.setChapterId(obj.get(i).getChapterId());
            schapter.setChapterName(obj.get(i).getChapterName());
            schapter.setCategoryId(obj.get(i).getCategoryId());
            security.setCategoryId(obj.get(i).getCategoryId());
            security.setChapterId(obj.get(i).getChapterId());
            security.setDangerSourceName(obj.get(i).getDangerSourceName());
            security.setExaminationContentId(obj.get(i).getExaminationContentId());
            security.setTypeId(obj.get(i).getTypeId());
            security.setTypeName(obj.get(i).getTypeName());
            security.setScore(obj.get(i).getScore());
            scontent.setContent(obj.get(i).getContent());
            scontent.setExaminationContentId(obj.get(i).getExaminationContentId());
            scontent.setMaxScore(obj.get(i).getMaxScore());
            scontent.setMinScore(obj.get(i).getMinScore());
            scontent.setRegulationId(obj.get(i).getRegulationId());
//            sregulation.setRegulationId(obj.get(i).getRegulationId());
//            sregulation.setRegulationTitle("");
//            sregulation.setRegulationContent(obj.get(i).getRegulationContent());

//            exceltemplateService.insertSchapter(schapter);
//            exceltemplateService.insertScontent(scontent);
            exceltemplateService.insertSecurity(security);
//            if(sregulation.getRegulationId()==null){
//                return ResInfo.error_database("RegulationId空");
//            }
//            else if(sregulation.getRegulationContent()==null){
//                return ResInfo.error_database("RegulationContent空");
//            }
//            else{
////                exceltemplateService.insertSregulation(sregulation);
//            }
        }
        exceltemplateService.deleteSecurityRepeatData(security.getChapterId());
//        exceltemplateService.deleteSchpaterRepeatData(schapter.getCategoryId());
        return ResInfo.success();
    }
}
