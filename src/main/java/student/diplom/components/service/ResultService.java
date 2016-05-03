package student.diplom.components.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.diplom.components.dao.ResultDao;
import student.diplom.components.entities.Result;

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
