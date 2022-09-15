package gcxy.security.mapper.checktable;

import gcxy.security.bean.Checktable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

@Mapper
public interface ChecktableMapper {

    /**
     * 插入打分表记录
     * @param record
     * @return
     */
    int insertRecord(Checktable record);

    /**
     * 根据用户id返回用户打分记录
     * @param checkPersonId
     * @return
     */
    CopyOnWriteArrayList<Checktable> getHistoryRecords(@Param("checkPersonId")String checkPersonId);

    /**
     * 返回所有人的打分id
     * @return
     */
    CopyOnWriteArraySet<String> getAllCheckPersonId();

    /**
     * 根据用户id返回用户打分记录
     * @param planId
     * @return
     */
    CopyOnWriteArrayList<Checktable> getHistoryRecordsByPlanId(@Param("planId") int planId);

    /**
     * 更新打分表记录
     * @param record
     * @return
     */
    int updateRecord(Checktable record);

    /**
     * 根据计划更新打分表记录
     * @param record
     * @return
     */
    int updateRecordByPlanIdAndChapterId(Checktable record);
}
