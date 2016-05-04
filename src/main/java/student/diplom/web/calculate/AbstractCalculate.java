package student.diplom.web.calculate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import student.diplom.web.entities.Param;
import student.diplom.web.entities.Result;
import student.diplom.web.entities.Value;
import student.diplom.web.models.SetParamWrongException;
import student.diplom.web.service.ParamService;
import student.diplom.web.service.ResultService;
import student.diplom.web.service.TypeTaskService;
import student.diplom.web.service.ValueService;

import java.util.*;

/**
 * Created by Андрей on 29.04.2016.
 */
@Component
public abstract class AbstractCalculate {

    @Autowired
    protected ResultService resultService;

    @Autowired
    protected ValueService valueService;

    @Autowired
    protected ParamService paramService;

    @Autowired
    protected TypeTaskService typeTaskService;

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
            Map <Param, Double> result = calculate(setValue);
            long totalTime = System.currentTimeMillis() - startTime;
            saverResult(setValue, result, totalTime);
            System.out.println(setValue);
        }
    }

    private void saverResult(Map<Param, Double> setValue, Map<Param, Double> setResult, long totalTime) {
        Result result = new Result();
        result.setTask(typeTaskService.find(setValue.keySet().iterator().next().getTypeTask().getId()));
        result.setTimeTask(totalTime);
        result.setClient(0);
        resultService.save(result);
        for(Param param: setValue.keySet()) {
            Value value = new Value();
            value.setResult(result);
            value.setParam(param);
            value.setValue(setValue.get(param));
            valueService.save(value);
        }
        for(Param param: setResult.keySet()) {
            Value value = new Value();
            value.setResult(result);
            value.setParam(param);
            value.setValue(setResult.get(param));
            valueService.save(value);
        }

    }

    protected abstract Map<Param, Double> calculate(Map<Param, Double> setValue) throws SetParamWrongException;


}
