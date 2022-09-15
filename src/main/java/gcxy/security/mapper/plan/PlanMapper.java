package gcxy.security.mapper.plan;

import gcxy.security.bean.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface PlanMapper {

    List getPlanByUserId(@Param("userId") String userId);

    List getPlanByPlanId(@Param("planId") Integer planId);

    void savePlan(Plan plan);

    void updatePlan(@Param("planId") int planId,@Param("chapterIds") String chapterIds,
                    @Param("targets") String targets,@Param("planName") String planName,
                    @Param("categoryId") String categoryId,@Param("planDate") String planDate);

    void deletePlanByPlanId(@Param("planId") int planId);
}
