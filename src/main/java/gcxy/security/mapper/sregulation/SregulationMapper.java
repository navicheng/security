package gcxy.security.mapper.sregulation;

import gcxy.security.bean.Sregulation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.concurrent.CopyOnWriteArrayList;

@Mapper
public interface SregulationMapper {
    /**
     * 返回所有的安全表依据
     * @return
     */
    CopyOnWriteArrayList<Sregulation> getAllSregulation ();

    /**
     * 返回指定的regulation_id对应的检查项
     * @param regulation_id
     * @return
     */
    CopyOnWriteArrayList<Sregulation> getSregulationByRegulationId(@Param("regulationId")int regulation_id);
}
