package student.diplom.web;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import student.diplom.web.calculate.AbstractCalculate;
import student.diplom.web.calculate.IntegralCalculate;
import student.diplom.web.entities.Param;
import student.diplom.web.models.IterateParam;
import student.diplom.web.models.Pack;
import student.diplom.web.models.SetParamWrongException;
import student.diplom.web.service.ResultService;
import student.diplom.config.SpringConfiguration;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

public class ClientNode {

    private static final Logger log = Logger.getLogger(ClientNode.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        ClientNode clientNode = new ClientNode();
        int port = 777;
        String host = "localhost";
        Socket socket = null;
        try {
            socket = new Socket(host, port);
        } catch (ConnectException e) {
            System.out.println("Connection is refused");
            return;
        }
        System.out.println("CONNECTED");

        OutputStream outStreamSocket = socket.getOutputStream();
        InputStream inStreamSocket = socket.getInputStream();

        PrintStream out = new PrintStream(outStreamSocket);
        ObjectInputStream in = new ObjectInputStream(inStreamSocket);

        out.println("Hello");

        Pack pack = (Pack) in.readObject();
        Map<Param, ListIterator<Double>> paramMap = new HashMap<>();
        for (IterateParam iterateParam : pack.getSetValues()) {
            paramMap.put(iterateParam.getParam(), iterateParam);
        }
        //AbstractCalculate calculator = new SumCalculate();
        AbstractCalculate calculator = new IntegralCalculate();
        try {
            calculator.init(paramMap);
        } catch (SetParamWrongException e) {
            System.out.println("Parameter set not correct");
        }
        in.close();
        out.close();
        outStreamSocket.close();
        inStreamSocket.close();

        log.warn("begin...");
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        ResultService resultService = context.getBean(ResultService.class);
        System.out.println(resultService.getResults());

    }
}
