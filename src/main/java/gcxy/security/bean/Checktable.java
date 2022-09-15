package gcxy.security.bean;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class Checktable {

    private int id;

    private Timestamp checkDate;

    private String checkLocation;

    private String checkPerson;

    private String checkPersonId;

    private String checkJson;

    private String checkSign;

    private String fileLocation;

    private String signLocation;

    private String signbLocation;

    private String checkId;

    private String chapterTitle;

    private String chapterId;

    private String target;

    private int planId;

}
