package gcxy.security.service;

import gcxy.security.bean.Plan;
import gcxy.security.mapper.plan.PlanMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional

public class PlanService {
    @Autowired(required = false)
    PlanMapper planMapper;

    /**
     * 返回指定userid的计划信息
     * @param userId
     * @return
     */
    public List getPlanByUserId(@Param("userId") String userId){
        List<Plan> plan= planMapper.getPlanByUserId(userId);
        return plan;
    }

    /**
     * 返回指定planid的计划信息
     * @param planId
     * @return
     */
    public List getPlanByPlanId(@Param("planId") Integer planId){
        List<Plan> plan= planMapper.getPlanByPlanId(planId);
        return plan;
    }

    /**
     * 保存指定userid的计划信息
     * @param userId
     * @param chapterIds
     * @param targets
     * @param planName
     * @param categoryId
     * @return
     */
    public Map<String,String> savePlan(@Param("userId") String userId,@Param("chapterIds") String chapterIds,
                         @Param("targets") String targets,@Param("planName") String planName,
                         @Param("categoryId") String categoryId,@Param("planDate") String planDate) {
        Map<String,String > map = new HashMap<>(2);
        Plan plan = new Plan();
        plan.setPlanId(0);
        plan.setUserId(userId);
        plan.setChapterIds(chapterIds);
        plan.setTargets(targets);
        plan.setPlanName(planName);
        plan.setCategoryId(categoryId);
        plan.setPlanDate(planDate);
        planMapper.savePlan(plan);
        map.put("planId:",String.valueOf(plan.getPlanId()));
        return map;
    }

    /**
     * 更新指定planId的计划信息
     * @param planId
     * @param chapterIds
     * @param targets
     * @param planName
     * @param categoryId
     * @return
     */
    public void updatePlan(@Param("planId") int planId,@Param("chapterIds") String chapterIds,
                           @Param("targets") String targets,@Param("planName") String planName,
                           @Param("categoryId") String categoryId,@Param("categoryId") String planDate){
        planMapper.updatePlan(planId,chapterIds,targets,planName,categoryId,planDate);
    }

    /**
     * 删除指定planId的计划信息
     * @param planId
     * @return
     */
    public void deletePlanByPlanId(@Param("planId") int planId){
        planMapper.deletePlanByPlanId(planId);
    }
}
