package gcxy.security.bean;

import lombok.Data;

@Data
public class ChartRule {
    private String examinationContentId;

    private String regulationId;

    private String ruleId;

    private String ruleContent;

    private float minScore;

    private float maxScore;

    private float realScore;

    private boolean checked;
}
