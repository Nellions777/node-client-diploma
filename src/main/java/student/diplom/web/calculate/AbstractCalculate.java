package student.diplom.web.calculate;

import student.diplom.web.entities.Param;
import student.diplom.web.models.SetParamWrongException;

import java.util.*;

/**
 * Created by Андрей on 29.04.2016.
 */
public abstract class AbstractCalculate {
    private Map<Param, ListIterator<Double>> paramMap;

    public void init(Map<Param, ListIterator<Double>> paramMap) throws SetParamWrongException {
        this.paramMap = paramMap;
        List<Param> keyList = new LinkedList<Param>(paramMap.keySet());
        ListIterator<Param> keyListIterator = keyList.listIterator();
        Map<Param, Double> mySetValue = new HashMap<>();
        calculate(keyListIterator, mySetValue);
    }

    private void calculate(ListIterator<Param> keyListIterator, Map<Param, Double> setValue) throws SetParamWrongException {
        if (keyListIterator.hasNext()) {
            Param currentParam = keyListIterator.next();
            while (paramMap.get(currentParam).hasNext()) {
                setValue.put(currentParam, paramMap.get(currentParam).next());
                calculate(keyListIterator, setValue);
            }
            while (paramMap.get(currentParam).hasPrevious()) {
                paramMap.get(currentParam).previous();
            }
            keyListIterator.previous();
        } else {
            long startTime = System.currentTimeMillis();
            long resultId = calculate(setValue);
            long totalTime = System.currentTimeMillis() - startTime;
            // TODO: getting typeTaskId or TypeTask.
            // TypeTaskID = "2" is id "Sum" Task
            //TypeTask typeTask = typeTaskService.findTaskById(2);
            //saveResult(resultId, typeTask, totalTime, setValue);
            System.out.println(setValue);
        }
    }

    protected abstract long calculate(Map<Param, Double> setValue) throws SetParamWrongException;


}
