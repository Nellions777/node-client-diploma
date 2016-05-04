package student.diplom.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import student.diplom.web.dao.ValueDao;
import student.diplom.web.entities.Value;

/**
 * Created by Евгений on 03.05.2016.
 */

@Transactional
@Service
public class ValueService {

    @Autowired
    private ValueDao dao;

    public void save(Value value) {
        dao.create(value);
    }
}
