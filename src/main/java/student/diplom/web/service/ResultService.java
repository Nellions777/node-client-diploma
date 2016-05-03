package student.diplom.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.diplom.web.dao.ResultDao;
import student.diplom.web.entities.Result;

import java.util.List;

/**
 * Created by Евгений on 03.05.2016.
 */

@Service
public class ResultService {

    @Autowired
    private ResultDao dao;

    public List<Result> getResults(){
        return dao.getResults();
    }
}
