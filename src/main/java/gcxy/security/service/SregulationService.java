package gcxy.security.service;

import gcxy.security.bean.Sregulation;
import gcxy.security.mapper.sregulation.SregulationMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Transactional
public class SregulationService {
    @Autowired(required = false)
    SregulationMapper sregulationMapper;

    /**
     * 返回所有的安全表依据
     * @return
     */
    public CopyOnWriteArrayList<Sregulation> getAllSregulation(){
        return sregulationMapper.getAllSregulation();
    }

    /**
     * 返回指定的regulation_id对应的安全检查项
     * @param regulationId
     * @return
     */
    public CopyOnWriteArrayList<Sregulation>getSregulationByRegulationId(@Param("regulationId") int regulationId){
        return sregulationMapper.getSregulationByRegulationId(regulationId);
    }
}
