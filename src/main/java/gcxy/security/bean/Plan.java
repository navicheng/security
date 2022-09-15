package gcxy.security.bean;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Data
public class Plan {

    private int planId;

    private String userId;

    private String chapterIds;

    private String planDate;

    private String targets;

    private String planName;

    private String categoryId;

}
