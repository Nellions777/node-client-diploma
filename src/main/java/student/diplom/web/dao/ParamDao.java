package student.diplom.web.dao;

import org.springframework.stereotype.Repository;
import student.diplom.web.entities.Param;
import student.diplom.web.entities.Value;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Евгений on 03.05.2016.
 */
@Repository
public class ParamDao extends JpaCRUD<Param> {

    public ParamDao(){
        setClazz(Param.class);
    }

    public List<Param> findCurrentParams(Boolean isInput, Long taskId){

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Param> criteriaQuery = builder.createQuery(Param.class);

        Root<Param> c = criteriaQuery.from(Param.class);
        criteriaQuery.select(c).where(
                builder.and(builder.equal(c.get("typeTask").get("id").as(Long.class), taskId), builder.equal(c.get("isInput").as(Boolean.class), isInput))
        );
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
