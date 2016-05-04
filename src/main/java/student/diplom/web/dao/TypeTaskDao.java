package student.diplom.web.dao;

import org.springframework.stereotype.Repository;
import student.diplom.web.entities.TypeTask;
import student.diplom.web.entities.Value;

/**
 * Created by Евгений on 03.05.2016.
 */
@Repository
public class TypeTaskDao extends JpaCRUD<TypeTask> {

    public TypeTaskDao(){
        setClazz(TypeTask.class);
    }


}
