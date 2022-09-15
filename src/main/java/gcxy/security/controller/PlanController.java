package gcxy.security.controller;


import gcxy.security.service.PlanService;
import gcxy.security.utils.ResInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api(tags = "计划管理")
public class PlanController {
    @Autowired
    PlanService planService;


    /**
     * 返回指定用户的计划项
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/plan/userId",method = RequestMethod.GET)
    @ApiOperation(value = "根据userId查询计划")
    public ResInfo getPlanByUserId(@Param("userId") String userId) {
        if (userId == null) {
            return ResInfo.error_param("缺少用户id");
        }
        return ResInfo.success(planService.getPlanByUserId(userId));
    }

    /**
     * 返回指定计划项
     *
     * @param planId
     * @return
     */
    @RequestMapping(value = "/plan/planId",method = RequestMethod.GET)
    @ApiOperation(value = "根据userId查询计划")
    public ResInfo getPlanByPlanId(@Param("planId") Integer planId) {
        if (planId == null) {
            return ResInfo.error_param("缺少用户id");
        }
        return ResInfo.success(planService.getPlanByPlanId(planId));
    }

    /**
     * 保存指定用户的计划项
     * @param userId
     * @param chapterIds
     * @param targets
     * @param planName
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "/plan",method = RequestMethod.POST)
    @ApiOperation(value = "保存计划")
    public ResInfo savePlan(@Param("userId") String userId,@Param("chapterIds") String chapterIds,
                            @Param("targets") String targets,@Param("planName") String planName,
                            @Param("categoryId") String categoryId,@Param("planDate") String planDate) {
        Map<String,String> map =planService.savePlan(userId,chapterIds,targets,planName,categoryId,planDate);
        return ResInfo.success(map);
    }

    /**
     * 更新指定的计划项
     * @param planId
     * @param chapterIds
     * @param targets
     * @param planName
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "/plan" ,method = RequestMethod.PUT)
    @ApiOperation(value = "根据planId更新计划")
    public ResInfo updatePlan(@Param("planId") int planId,@Param("chapterIds") String chapterIds,
                              @Param("targets") String targets,@Param("planName") String planName,
                              @Param("categoryId") String categoryId,@Param("planDate") String planDate) {
        try {
            planService.updatePlan(planId,chapterIds,targets,planName,categoryId,planDate);
        } catch (Exception e) {
            return ResInfo.error_normal(String.valueOf(e));
        }
        return ResInfo.success("更新完成");
    }

    /**
     * 删除指定计划项
     *
     * @param planId
     * @return
     */
    @RequestMapping(value = "/plan" ,method = RequestMethod.DELETE)
    @ApiOperation(value = "根据planId删除计划")
    public ResInfo deletePlanByPlanId(@Param("planId") int planId) {
        try {
            planService.deletePlanByPlanId(planId);
        } catch (Exception e) {
            return ResInfo.error_normal(String.valueOf(e));
        }
        return ResInfo.success("删除完成");
    }
}
