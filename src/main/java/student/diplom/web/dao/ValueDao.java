package student.diplom.web.dao;

import org.springframework.stereotype.Repository;
import student.diplom.web.entities.Result;
import student.diplom.web.entities.Value;

import java.util.List;

/**
 * Created by Евгений on 03.05.2016.
 */
@Repository
public class ValueDao extends JpaCRUD<Value> {

    public ValueDao(){
        setClazz(Value.class);
    }


}
