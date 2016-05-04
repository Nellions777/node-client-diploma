package student.diplom.web.calculate;

import student.diplom.web.entities.Param;
import student.diplom.web.models.SetParamWrongException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Андрей on 29.04.2016.
 */
public class SumCalculate extends AbstractCalculate {

    private long TASK_ID = 2;


    @Override
    public Map<Param, Double> calculate(Map<Param, Double> setValue) throws SetParamWrongException {
        Double g = setValue.get(new Param("g"));
        Double f = setValue.get(new Param("f"));
        Double d = setValue.get(new Param("d"));
        if (g == null || f == null || d == null) {
            throw new SetParamWrongException();
        } else {
            return calculate(g, f, d);
        }
    }

    public Map<Param, Double> calculate(double a, double b, double c) {
        Map<Param, Double> resultMap = new HashMap<>();
        List<Param> resultParams = paramService.findCurrentParams(false, TASK_ID);
        Param resultParam = resultParams.get(0);
        resultMap.put(resultParam, a + b + c);
        return resultMap;
    }
}
