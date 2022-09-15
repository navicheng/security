package gcxy.security.service;

import gcxy.security.bean.Qregulation;
import gcxy.security.mapper.qregulation.QregulationMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Transactional
public class QregulationService {
    @Autowired(required = false)
    QregulationMapper qregulationMapper;

    /**
     * 返回所有的质量表依据
     * @return
     */
    public CopyOnWriteArrayList<Qregulation>getAllQregulation(){
        return qregulationMapper.getAllQregulation();
    }

    /**
     * 返回指定的regulation_id对应的质量检查项
     * @param regulationId
     * @return
     */
    public CopyOnWriteArrayList<Qregulation>getQregulationByRegulationId(@Param("regulationId") int regulationId){
        return qregulationMapper.getQregulationByRegulationId(regulationId);
    }
}
