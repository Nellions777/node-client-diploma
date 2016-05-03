package student.diplom.components.dao;

import org.springframework.stereotype.Repository;
import student.diplom.components.entities.Result;

import java.util.List;

/**
 * Created by Евгений on 03.05.2016.
 */
@Repository
public class ResultDao extends JpaCRUD<Result> {

    public ResultDao(){
        setClazz(Result.class);
    }

    public List<Result> getResults(){
        return findAll();
    }
}
