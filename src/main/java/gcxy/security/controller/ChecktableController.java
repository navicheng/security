package gcxy.security.controller;

import com.alibaba.fastjson.JSONObject;
import gcxy.security.bean.*;
import gcxy.security.service.ChecktableService;
import gcxy.security.service.SchapterService;
import gcxy.security.service.ScontentService;
import gcxy.security.service.SecurityService;
import gcxy.security.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/checktable")
@Api(tags = "检查表管理", description = "关于检查表的操作")
public class ChecktableController {
    @Autowired
    ChecktableService checktableService;
    @Autowired
    SecurityService securityService;
    @Autowired
    SchapterService schapterService;
    @Autowired
    ScontentService scontentService;


    @GetMapping("/checkJson")
    @ApiOperation(value = "返回checkJson新对象")
    public ResInfo checkJson(@Param("chapterId") String chapterId){
        Checkjson checkjson=new Checkjson();
        CopyOnWriteArrayList<Security> records = securityService.getSecurityRecordByChapterId(chapterId);
        //schapter find name
        String chapterTitle = schapterService.getSchapterNameByChapterId(chapterId);
        checkjson.setChapterId(chapterId);
        checkjson.setChapterTitle(chapterTitle);
        checkjson.setRealScore(100);
        checkjson.setCharts(new ArrayList<>());

        for (Security record : records) {
            Chart chart = new Chart();
            chart.setChartId(record.getExaminationContentId());
            chart.setChartTitle(record.getDangerSourceName());
            chart.setRealScore(Float.parseFloat(record.getScore()));
            chart.setScore(Float.parseFloat(record.getScore()));
            chart.setTypeId(record.getTypeId());
            chart.setTypeName(record.getTypeName());
            chart.setRules(new ArrayList<>());

            for (Scontent scontent: scontentService.getScontentByExaminationContentId(record.getExaminationContentId())){
                ChartRule rule = new ChartRule();
                rule.setRealScore(Float.parseFloat(scontent.getMinScore()));
                rule.setMinScore(Float.parseFloat(scontent.getMinScore()));
                rule.setMaxScore(Float.parseFloat(scontent.getMaxScore()));
                rule.setExaminationContentId(scontent.getExaminationContentId());
                rule.setRegulationId(scontent.getRegulationId());
                rule.setChecked(false);
                rule.setRuleContent(scontent.getContent());
                chart.getRules().add(rule);
            }
            checkjson.getCharts().add(chart);
        }
        return ResInfo.success(checkjson);
    }

    @PostMapping("/preview")
    @ApiOperation(value = "预览")
    public void preview(@RequestParam("checkjson") String checkjson, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Checkjson checkJson = JsonUtils.jsonHandle(checkjson);

        String path = "/usr/check/review/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime()) + "/" + checkJson.getCheckPerson() + "/" + checkJson.getChapterTitle() + ".pdf";
//        String path = "D:/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime()) + "/" + checkjson.getCheckPerson() + "/" + checkjson.getChapterTitle() + ".pdf";
        PDFUtils.fileOperation(PDFUtils.generateFile(path), checkJson);
        //System.out.println(path);
        FileUtils.downloadFile(request, response, new File(path));
    }


    @PostMapping("/signSubmit")
    @ApiOperation(value = "签名提交")
    public ResInfo signSubmit(@RequestParam("checkjson") String checkjson, @RequestPart("file") MultipartFile file,@RequestPart("fileB") MultipartFile fileB,@Param("id") String id) {
        //赋值
        Timestamp checkDate = new Timestamp(new Date().getTime());
        Checkjson json = JsonUtils.jsonHandle(checkjson);
        Checktable checktable = new Checktable();
        checktable.setCheckDate(checkDate);
        checktable.setCheckLocation(json.getCheckLocation());
        checktable.setCheckPerson(json.getCheckPerson());
        checktable.setCheckPersonId(json.getCheckPersonId());
        checktable.setCheckJson(checkjson);
        checktable.setChapterId(json.getChapterId());
        checktable.setChapterTitle(json.getChapterTitle());
        checktable.setPlanId(json.getPlanId());
        checktable.setCheckSign(String.valueOf(1));
        if(id!=null && !id.isEmpty()){
            checktable.setId(Integer.parseInt(id));
        }
        String signPicPath = "/usr/check/records/signPIC/personA" + "/" + json.getCheckPerson() +"/" + json.getPlanId() + "/"+ json.getChapterTitle() + "/";
        String signPicPathB = "/usr/check/records/signPIC/personB" + "/" + json.getCheckPerson() +"/" + json.getPlanId() + "/"+ json.getChapterTitle() + "/";
        String signPath = "/usr/check/records/signPDF/" + json.getCheckPerson() + "/" +json.getPlanId()+ "/" +json.getChapterTitle()+ ".pdf";

//        String signPicPath = "D:"  + "/"+ json.getCheckPerson() +"/" + json.getPlanId() + "/"+ json.getChapterTitle() + "/personA/";
//        String signPath = "D:/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime()) + "/" + json.getCheckPerson() + "/" + json.getChapterTitle() + ".pdf";
//        String signPicPathB = "D:"  + "/"+ json.getCheckPerson() +"/" + json.getPlanId() + "/"+ json.getChapterTitle() + "/personB/";

        checktable.setSignLocation(signPicPath + file.getOriginalFilename());
        checktable.setSignbLocation(signPicPathB + fileB.getOriginalFilename());
        checktable.setFileLocation(signPath);
        FileUtils.uploadFile(file, signPicPath);//保存签名
        FileUtils.uploadFile(fileB, signPicPathB);//保存签名
        if(id==null || id.isEmpty()){
            checktable.setCheckSign(String.valueOf(1));
            checktableService.insertRecord(checktable);
        }
        if(id!=null && !id.isEmpty()){
            checktable.setCheckSign(String.valueOf(1));
            checktableService.updateRecord(checktable);
        }
        PDFUtils.fileOperation(PDFUtils.generateFile(signPath),json,signPicPath+file.getOriginalFilename()+".jpg",signPicPathB+fileB.getOriginalFilename()+".jpg");
        return ResInfo.success("文件保存成功");
    }

    @PostMapping("/noSignSubmit")
    @ApiOperation(value = "不签名提交")
    public ResInfo noSignSubmit(@RequestParam("checkjson") String checkjson) {
        //赋值
        Timestamp checkDate = new Timestamp(new Date().getTime());
        Checkjson json = JsonUtils.jsonHandle(checkjson);
        Checktable checktable = new Checktable();
        checktable.setCheckDate(checkDate);
        checktable.setCheckLocation(json.getCheckLocation());
        checktable.setCheckPerson(json.getCheckPerson());
        checktable.setCheckPersonId(json.getCheckPersonId());
        checktable.setCheckJson(checkjson);
        checktable.setChapterId(json.getChapterId());
        checktable.setChapterTitle(json.getChapterTitle());
        checktable.setPlanId(json.getPlanId());

        //生成保存后的文件
//        String signPath = "D:/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime()) + "/" + json.getCheckPerson() + "/" + json.getChapterTitle() + ".pdf";
        String signPath = "/usr/check/records/signPDF/" + json.getCheckPerson() + "/" +json.getPlanId()+ "/" +json.getChapterTitle()+ ".pdf";
        PDFUtils.fileOperation(PDFUtils.generateFile(signPath), json);
        checktable.setFileLocation(signPath);
        checktable.setCheckSign(String.valueOf(0));
        checktableService.insertRecord(checktable);
        return ResInfo.success("文件保存成功");
    }

    @PostMapping("/updateRecord")
    @ApiOperation(value = "修改表格")
    public ResInfo updateRecord(@RequestParam("checkjson") String checkjson,@Param("id") String id) {
        //赋值
        Timestamp checkDate = new Timestamp(new Date().getTime());
        Checkjson json = JsonUtils.jsonHandle(checkjson);
        Checktable checktable = new Checktable();
        checktable.setCheckDate(checkDate);
        checktable.setCheckLocation(json.getCheckLocation());
        checktable.setCheckPerson(json.getCheckPerson());
        checktable.setCheckPersonId(json.getCheckPersonId());
        checktable.setCheckJson(checkjson);
        checktable.setChapterId(json.getChapterId());
        checktable.setChapterTitle(json.getChapterTitle());
        checktable.setPlanId(json.getPlanId());

//        String signPath = "D:/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime()) + "/" + json.getCheckPerson() + "/" + json.getChapterTitle() + ".pdf";
        String signPath = "/usr/check/records/signPDF/" + json.getCheckPerson() + "/" +json.getPlanId()+ "/" +json.getChapterTitle()+ ".pdf";
        PDFUtils.fileOperation(PDFUtils.generateFile(signPath), json);
        checktable.setFileLocation(signPath);
        checktable.setId(Integer.parseInt(id));
        checktableService.updateRecord(checktable);
        return ResInfo.success("文件修改成功");
    }
        /**
         * 根据用户id返回用户打分记录
         *
         * @param checkPersonId
         * @return
         */
    @GetMapping("/history")
    @ApiOperation(value = "历史记录")
    public ResInfo getHistoryRecords(@Param("checkPersonId") String checkPersonId) {
        if (checktableService.getAllCheckPersonId().contains(checkPersonId)) {
            if (checktableService.getHistoryRecords(checkPersonId).size() == 0) {
                return ResInfo.error_database("数据库无数据");
            }
            return ResInfo.success(checktableService.getHistoryRecords(checkPersonId));
        }
        return ResInfo.error_param("id错误，数据库无对应人员id");
    }

    /**
     * 根据planId返回打分记录
     *
     * @param planId
     * @return
     */
    @GetMapping("/plan")
    @ApiOperation(value = "查询计划历史记录")
    public ResInfo getHistoryRecordsByPlanId(@Param("planId") int planId) {
        CopyOnWriteArrayList<Checktable> historyRecords=checktableService.getHistoryRecordsByPlanId(planId);
        if (historyRecords.size() == 0) {
                return ResInfo.error_database("数据库无对应历史记录");
            }
            return ResInfo.success(historyRecords);
    }

    @PostMapping("/download")
    @ApiOperation(value = "下载文件")
    public void download(@Param("checkjson") String checkjson, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Checkjson checJson = JsonUtils.jsonHandle(checkjson);
//        String signPath = "D:/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime()) + "/"  +checJson.getPlanId()+"/"+ checJson.getCheckPerson() + "/" + checJson.getChapterTitle() + ".pdf";
        String signPath = "/usr/check/records/signPDF/"  + checJson.getCheckPerson() + "/" +checJson.getPlanId()+ "/" +checJson.getChapterTitle()+ ".pdf";
        FileUtils.downloadFile(request, response, new File(signPath));
    }

    @PostMapping("/draw")
    @ApiOperation(value = "画图")
    public void draw(@RequestParam("checkjson") String checkjson, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Checkjson json = JsonUtils.jsonHandle(checkjson);
        Checktable checktable = new Checktable();
        checktable.setCheckLocation(json.getCheckLocation());
        checktable.setCheckPerson(json.getCheckPerson());
        checktable.setCheckPersonId(json.getCheckPersonId());
        checktable.setCheckJson(checkjson);
        checktable.setChapterId(json.getChapterId());
        checktable.setChapterTitle(json.getChapterTitle());
        checktable.setPlanId(json.getPlanId());

        //生成保存后的文件
//        String signPath = "D:/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime()) + "/" +json.getPlanId()+"/"+ json.getCheckPerson() + "/" + json.getChapterTitle() + ".pdf";
        String signPath = "/usr/check/records/signPDF/"  + json.getCheckPerson() + "/" +json.getPlanId()+ "/" +json.getChapterTitle()+ ".pdf";
        PDFUtils.fileDrawOperation(PDFUtils.generateFile(signPath), json);
        checktable.setFileLocation(signPath);
        checktable.setCheckSign(String.valueOf(0));
        checktableService.insertRecord(checktable);
        FileUtils.downloadFile(request, response, new File(signPath));
    }

    @PostMapping("/upLoad") 
    @ApiOperation(value = "上传文件")
    public ResInfo upLoad(@RequestParam("checkjson") String checkjson,@RequestPart("file") MultipartFile file) throws Exception {
        Checkjson json = JsonUtils.jsonHandle(checkjson);
        Checktable checktable = new Checktable();
        checktable.setCheckLocation(json.getCheckLocation());
        checktable.setCheckPerson(json.getCheckPerson());
        checktable.setCheckPersonId(json.getCheckPersonId());
        checktable.setCheckJson(checkjson);
        checktable.setChapterId(json.getChapterId());
        checktable.setChapterTitle(json.getChapterTitle());
        checktable.setPlanId(json.getPlanId());
        checktable.setCheckSign(String.valueOf(2));
        //生成保存后的文件
//        String signPath="D:\\" + new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime()) + "\\" +json.getPlanId()+"\\"+ json.getCheckPerson() + "\\";
        String signPath = "/usr/check/records/signPDF/" + json.getCheckPerson() + "/" +json.getPlanId()+ "/" ;
        File des=new File(signPath+ json.getChapterTitle() + ".pdf");
        if (des.exists()) {
            des.delete();
        }
        FileUtils.uploadDrawFile(file,signPath);
        checktable.setFileLocation(signPath+ json.getChapterTitle() + ".pdf");
        checktableService.updateRecordByPlanIdAndChapterId(checktable);
        return ResInfo.success("保存成功");
    }

}
