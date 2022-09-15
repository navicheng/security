package gcxy.security.bean;

import lombok.Data;

@Data
public class Exceltemplate {
    private String categoryName;//分类名

    private String categoryId;//分类id

    private String chapterName;//章节名称

    private String chapterId;//章节号  对应的是schapter

    private String typeName;//保证项目还是一般项目

    private String typeId;// 项目id

    private String dangerSourceName;//危险源 对应security

    private String examinationContentId;//危险源id security scontent

    private String content;//检查内容 对应scontent

    private String regulationId;//contentid  scontent sregulation

    private String minScore;

    private String maxScore;

    private String score;//单项得分

    private String regulationContent;//依据内容

    private String regulationTitle;//依据名称


    public Exceltemplate(
            String categoryName, String categoryId, String chapterName, String chapterId,
            String typeName, String typeId, String dangerSourceName,
            String examinationContentId, String content,  String minScore, String maxScore, String score,
//            String regulationTitle,
//            String regulationContent,
            String regulationId
    ) {
        this.categoryName = categoryName;
        this.categoryId = categoryId;
        this.chapterName = chapterName;
        this.chapterId = chapterId;
        this.typeName = typeName;
        this.typeId = typeId;
        this.dangerSourceName = dangerSourceName;
        this.examinationContentId = examinationContentId;
        this.content = content;
        this.regulationId = regulationId;
        this.minScore = minScore;
        this.maxScore = maxScore;
        this.score = score;
//        this.regulationContent = regulationContent;
//        this.regulationTitle = regulationTitle;
    }
}
