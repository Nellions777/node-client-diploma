package student.diplom.components;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import student.diplom.components.service.ResultService;
import student.diplom.config.SpringConfiguration;

public class HelloApp {

    private static final Logger log = Logger.getLogger(HelloApp.class);

    public static void main(String[] args) {
        log.warn("LOL!!!");
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        ResultService resultService = context.getBean(ResultService.class);
        System.out.println(resultService.getResults());
    }
}
