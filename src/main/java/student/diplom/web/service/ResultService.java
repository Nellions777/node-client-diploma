package student.diplom.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import student.diplom.web.dao.ResultDao;
import student.diplom.web.entities.Result;

import java.util.List;

/**
 * Created by Евгений on 03.05.2016.
 */

@Transactional
@Service
public class ResultService {

    @Autowired
    private ResultDao dao;

    public Result find(long resultId) {
        return dao.findOne(resultId);
    }

    public List<Result> getResults(){
        return dao.getResults();
    }

    public void save(Result result) {
        dao.create(result);
    }
}
