package student.diplom.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import student.diplom.web.dao.TypeTaskDao;
import student.diplom.web.dao.ValueDao;
import student.diplom.web.entities.Param;
import student.diplom.web.entities.TypeTask;
import student.diplom.web.entities.Value;

/**
 * Created by Евгений on 03.05.2016.
 */

@Transactional
@Service
public class TypeTaskService {

    @Autowired
    private TypeTaskDao dao;

    public TypeTask find(long typeTaskId) {
        return dao.findOne(typeTaskId);
    }

    public void save(TypeTask typeTask) {
        dao.create(typeTask);
    }
}
