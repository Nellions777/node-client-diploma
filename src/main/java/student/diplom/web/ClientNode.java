package student.diplom.web;

import org.apache.log4j.Logger;
import javax.swing.*;
import java.io.*;

public class ClientNode extends JFrame{

    private static final Logger log = Logger.getLogger(ClientNode.class);

    private SettingsPanel settingsPanel;

    public ClientNode (String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        settingsPanel = new SettingsPanel();
        add(settingsPanel);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        final ClientNode clientNode = new ClientNode("Calculate client");
    }
}
