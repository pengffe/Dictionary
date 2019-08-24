import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class UserInterface extends JFrame {

    private JFrame fDictionary;

    private JButton btQuery;
    private JButton btAdd;
    private JButton btDelete;

    private JTextField tfWord;
    private JTextField tfMeaning;

    private JPanel jp1;
    private JPanel jp2;
    private JPanel jp3;


    private Socket socket = null;
    private BufferedReader in;
    private BufferedWriter out;



    public UserInterface(){

        fDictionary = new JFrame("Dictionary");

        //basic setting on frame
        fDictionary.setSize(500,300);

        //Set FlowLayout,aligned left with horizontal gap 10
        //and vertical gap 20 between components
        fDictionary.setLayout(new FlowLayout(FlowLayout.CENTER, 30,20));

        //add label
        JLabel laWord = new JLabel("Word");
        JLabel laMeaning = new JLabel("Meaning");


        //add button
        btQuery = new JButton("Query");
        btAdd = new JButton("Add");
        btDelete = new JButton("Delete");
        ButtonGroup bg = new ButtonGroup();
        bg.add(btAdd);
        bg.add(btDelete);
        bg.add(btQuery);

        //add textfield
        tfWord = new JTextField(12);
        tfMeaning = new JTextField(40);

        setLayout(new GridLayout(3, 1));
        tfWord.setFont(new Font(null, Font.PLAIN, 11));
        tfMeaning.setFont(new Font(null, Font.PLAIN, 11));

        //word panel
        JPanel jp1 = new JPanel();
        jp1.add(laWord);
        jp1.add(tfWord);
        fDictionary.add(jp1);

        //meaning panel;
        JPanel jp2 = new JPanel();
        jp2.add(laMeaning);
        jp2.add(tfMeaning);
        fDictionary.add(jp2);

        //btn panel
        JPanel jp3 = new JPanel();
        jp3.add(btAdd);
        jp3.add(btDelete);
        jp3.add(btQuery);
        fDictionary.add(jp3);


        fDictionary.setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //load event on frame
        myEvent();
    }

    public void initial(){
        try{
            //Create a stream socket bounded to any port and connect it to the
            //socket bound to localhost on port 2048
            socket = new Socket("localhost", 4444);
            System.out.println("Connection established");

            //Get the input/output streams for reading/writing data from/to the socket
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            //Scanner scanner = new Scanner(System.in);
            //String inputStr = null;
            while(true){
                receive();
            }
        }
        catch(UnknownHostException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally {
            //Close the socket
            if(socket != null){
                try{
                    socket.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void receive(){
            //Receive the reply from the server by reading from the socket input stream
            try{
                String received = in.readLine(); // This method blocks until there  is something to read from the
                // input stream
                System.out.println("Message received: " + received);
                tfMeaning.setText(received);
            }
           catch(IOException e){
                e.printStackTrace();
           }
    }

    public void myEvent(){
        String inputStr = "explicit";
        btQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("开始查询！");
                try{
                    out.write( tfWord.getText()+ "\n");
                    out.flush();
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        });
        fDictionary.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }
}

