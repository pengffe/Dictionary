import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class UserInterface extends JFrame {

    private Frame fDictionary;
    private Button btQuery;

    public UserInterface(){

        fDictionary = new Frame("Dictionary");

        //basic setting on frame
        fDictionary.setSize(200,200);
        fDictionary.setVisible(true);
        //Set FlowLayout,aligned left with horizontal gap 10
        //and vertical gap 20 between components
        fDictionary.setLayout(new FlowLayout(FlowLayout.CENTER, 20,20));

        btQuery = new Button("Query");

        fDictionary.add(btQuery);

        //load event on frame
        myEvent();

        //Add labels and text fields to the frame
//        add(new JLabel("Word"));
//        add(new JTextField(8));
//
//        add(new JLabel("Meaning"));
//        add(new JTextField(8));
    }

    public void myEvent(){
        btQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("开始查询！");
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
