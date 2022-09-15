package gcxy.security.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class Checkjson implements Serializable {

    private String chapterId;

    private String chapterTitle;

    private String checkLocation;

    private String checkPerson;

    private String checkPersonId;

    private int planId;

    private float realScore;

    private ArrayList<Chart> charts;

    private String id;




}
