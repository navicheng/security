package gcxy.security.bean;

import lombok.Data;

import java.util.ArrayList;
@Data
public class Chart {
    private String chartId;

    private String chartTitle;

    private float realScore;

    private float score;

    private String typeId;

    private String typeName;

    private ArrayList<ChartRule> rules;



}
