package student.diplom.web.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by Евгений on 11.04.2016.
 */

@Entity
public class Result {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private TypeTask typeTask;

    private Long timeTask;

    private Integer client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeTask getTypeTask() {
        return typeTask;
    }

    public void setTypeTask(TypeTask typeTask) {
        this.typeTask = typeTask;
    }

    public Long getTimeTask() {
        return timeTask;
    }

    public void setTimeTask(Long timeTask) {
        this.timeTask = timeTask;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Result{"+ id + "; "+ typeTask.getName() + "; time: " + timeTask + "; client: " + client +"}";
    }
}
