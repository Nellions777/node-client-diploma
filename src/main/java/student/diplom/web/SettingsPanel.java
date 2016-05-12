package student.diplom.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import student.diplom.config.SpringConfiguration;
import student.diplom.web.calculate.AbstractCalculate;
import student.diplom.web.calculate.IntegralCalculate;
import student.diplom.web.entities.Param;
import student.diplom.web.models.IterateParam;
import student.diplom.web.models.Pack;
import student.diplom.web.models.SetParamWrongException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by Андрей on 05.05.2016.
 */
public class SettingsPanel extends  JPanel{
    private JLabel hostLabel;
    private JTextField hostField;
    private JLabel portLabel;
    private JTextField portField;
    private JButton connectButton;
    private JButton startButton;
    private JTextArea chat;

    private Socket socket;
    private Pack pack = null;

    public SettingsPanel() {
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setBackground(Color.BLUE);
        hostLabel = new JLabel("host:");
        topPanel.add(hostLabel, BorderLayout.NORTH);
        hostField = new JTextField("127.0.0.1", 10);
        topPanel.add(hostField, BorderLayout.NORTH);
        portLabel = new JLabel("port:");
        topPanel.add(portLabel, BorderLayout.NORTH);
        portField = new JTextField("777", 5);
        topPanel.add(portField, BorderLayout.NORTH);
        connectButton = new JButton("connect");
        startButton = new JButton("calculate");
        startButton.setEnabled(false);
        topPanel.add(connectButton, BorderLayout.NORTH);
        topPanel.add(startButton, BorderLayout.NORTH);
        add(topPanel, BorderLayout.NORTH);
        JPanel eastPanel = new JPanel();
        eastPanel.setBackground(Color.BLUE);
        chat = new JTextArea(25, 25);
        chat.setBackground(Color.YELLOW);
        chat.setEditable(false);
        chat.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(chat, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        eastPanel.add(scroll);
        add(eastPanel, BorderLayout.EAST);
        setBackground(Color.BLUE);
        connectButton.addActionListener(new ConnectClick());
        startButton.addActionListener(new CalculateClick());
    }

    private void clientPrint(String text) {
        chat.append("CLIENT: " + text + "\n");
    }

    private void serverPrint(String text) {
        chat.append("SERVER: " + text + "\n");
    }

    class ConnectClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(pack != null) {
                clientPrint("Calculate getted pack, please");
                return;
            }
            int port = Integer.parseInt(portField.getText());
            String host = hostField.getText();
            try {
                socket = new Socket(host, port);
                startButton.setEnabled(true);
            } catch (ConnectException e1) {
                clientPrint("Connection is refused");
                return;
            } catch (UnknownHostException e1) {
                clientPrint(e1.getMessage());
                return;
            } catch (IOException e1) {
                clientPrint(e1.getMessage());
                return;
            }
            clientPrint("CONNECTED");
            OutputStream outStreamSocket = null;
            InputStream inStreamSocket = null;
            try {
                outStreamSocket = socket.getOutputStream();
                inStreamSocket = socket.getInputStream();
            } catch (IOException e1) {
                clientPrint(e1.getMessage());
            }

            PrintStream out = new PrintStream(outStreamSocket);
            ObjectInputStream in = null;
            try {
                in = new ObjectInputStream(inStreamSocket);
            } catch (IOException e1) {
                clientPrint(e1.getMessage());
            }

            out.println("Hello");
            try {
                pack = (Pack) in.readObject();
                serverPrint(pack.toString());
            } catch (IOException e1) {
                clientPrint(e1.getMessage());
            } catch (ClassNotFoundException e1) {
                clientPrint(e1.getMessage());
            }
            try {
                out.close();
                in.close();
                outStreamSocket.close();
                inStreamSocket.close();
            } catch (IOException e1) {
                clientPrint(e1.getMessage());
            }

        }
    }
    class CalculateClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Map<Param, ListIterator<Double>> paramMap = new HashMap<>();
            for (IterateParam iterateParam : pack.getSetValues()) {
                paramMap.put(iterateParam.getParam(), iterateParam);
            }
            ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
            //AbstractCalculate calculator = new SumCalculate();
            //AbstractCalculate calculator = new IntegralCalculate();
            AbstractCalculate calculator = context.getBean(IntegralCalculate.class);
            try {
                calculator.init(paramMap);
                clientPrint("Calculating finished");
            } catch (SetParamWrongException e1) {
                clientPrint("Parameter set not correct");
            }
            pack = null;
            startButton.setEnabled(false);

        }
    }
}
