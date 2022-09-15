package gcxy.security.mapper.qregulation;

import gcxy.security.bean.Qregulation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.concurrent.CopyOnWriteArrayList;

@Mapper
public interface QregulationMapper {

    /**
     * 返回所有的质量表依据
     *
     * @return
     */
    CopyOnWriteArrayList<Qregulation> getAllQregulation();

    /**
     * 返回指定的regulation_id对应的检查项
     *
     * @param regulation_id
     * @return
     */
    CopyOnWriteArrayList<Qregulation> getQregulationByRegulationId(@Param("regulationId") int regulation_id);
}
