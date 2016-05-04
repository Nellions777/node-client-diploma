package student.diplom.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import student.diplom.web.dao.ParamDao;
import student.diplom.web.entities.Param;

import java.util.List;

/**
 * Created by Евгений on 03.05.2016.
 */

@Transactional
@Service
public class ParamService {

    @Autowired
    private ParamDao dao;

    public List<Param> findCurrentParams(Boolean isInput, long taskId){
        return dao.findCurrentParams(isInput, taskId);
    }

    public Param find(long paramId) {
        return dao.findOne(paramId);
    }

    public void save(Param param) {
        dao.create(param);
    }
}
