package gcxy.security.service;

import gcxy.security.bean.Checktable;
import gcxy.security.mapper.checktable.ChecktableMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
@Transactional
public class ChecktableService {
    @Autowired(required = false)
    ChecktableMapper checktableMapper;

    /**
     * 插入打分表记录
     *
     * @param record
     * @return
     */
    public int insertRecord(Checktable record) {
        return checktableMapper.insertRecord(record);

    }

    /**
     * 根据用户id返回用户打分记录
     *
     * @param checkPersonId
     * @return
     */
    public CopyOnWriteArrayList<Checktable> getHistoryRecords(@Param("checkPersonId") String checkPersonId) {
        return checktableMapper.getHistoryRecords(checkPersonId);
    }

    /**
     * 返回所有人的打分id
     *
     * @return
     */
    public CopyOnWriteArraySet<String> getAllCheckPersonId() {
        return checktableMapper.getAllCheckPersonId();
    }

    /**
     * 根据用户id返回用户打分记录
     *
     * @param planId
     * @return
     */
    public CopyOnWriteArrayList<Checktable> getHistoryRecordsByPlanId(@Param("planId") int planId) {
        return checktableMapper.getHistoryRecordsByPlanId(planId);
    }

    /**
     * 更新打分表记录
     *
     * @param record
     * @return
     */
    public int updateRecord(Checktable record) {
        return checktableMapper.updateRecord(record);
    }

    /**
     * 根据计划更新打分表记录
     *
     * @param record
     * @return
     */
    public int updateRecordByPlanIdAndChapterId(Checktable record) {
        return checktableMapper.updateRecordByPlanIdAndChapterId(record);
    }
}
