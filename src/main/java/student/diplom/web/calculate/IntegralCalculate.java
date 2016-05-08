package student.diplom.web.calculate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import student.diplom.web.entities.Param;
import student.diplom.web.entities.Value;
import student.diplom.web.models.SetParamWrongException;
import student.diplom.web.service.ParamService;
import student.diplom.web.service.ResultService;
import student.diplom.web.service.TypeTaskService;
import student.diplom.web.service.ValueService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Андрей on 29.04.2016.
 */
@Component
public class IntegralCalculate extends AbstractCalculate {

    private int DIV_NUMBER = 100;
    private long TASK_ID = 1;

    @Override
    public Map<Param, Double> calculate(Map<Param, Double> setValue) throws SetParamWrongException {
        Double a = setValue.get(new Param("a"));
        Double b = setValue.get(new Param("b"));
        Double c = setValue.get(new Param("c"));
        Double s = setValue.get(new Param("s"));
        if (a == null || b == null || c == null || s == null) {
            throw new SetParamWrongException();
        } else {
            return function(a, b, c, s, DIV_NUMBER);
        }
    }

    public Map<Param, Double> function(double a, double b, double c, double s, int n) {
        Map<Param, Double> resultMap = new HashMap<>();
        List<Param> resultParams = paramService.findCurrentParams(false, TASK_ID);
        Param resultParam = resultParams.get(0);
        double d = (b - a) / n;
        double result = 0;
        double xm;
        double ym;
        for (int i = 0; i < n; i++) {
            xm = a + i * d + d / 2;
            // ym = d * xm * xm;
            ym = c * Math.sin(Math.pow(Math.E, s * xm));
            result += d * ym;
        }
        resultMap.put(resultParam, result);
        return resultMap;
    }
}
