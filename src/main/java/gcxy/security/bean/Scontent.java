package gcxy.security.bean;

import lombok.Data;

@Data
public class Scontent {

    private String content;

    private String minScore;

    private String maxScore;

    private String examinationContentId;

    private String regulationId;
}
