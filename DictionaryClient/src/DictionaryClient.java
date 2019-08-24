import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class DictionaryClient extends JFrame {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        UserInterface ui = new UserInterface();
        ui.initial();

//        Socket socket = null;
//        try{
//            //Create a stream socket bounded to any port and connect it to the
//            //socket bound to localhost on port 2048
//            socket = new Socket("localhost", 4444);
//            System.out.println("Connection established");
//
//            //Get the input/output streams for reading/writing data from/to the socket
//            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
//            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
//
//            UserInterface frame = new UserInterface();
//            Scanner scanner = new Scanner(System.in);
//            String inputStr = null;
//
//            //While the user input differs from "exit
//            while (!(inputStr = scanner.next()).equals("exit"))
//            {
//
//                // Send the input string to the server by writing to the socket output stream
//                out.write(inputStr + "\n");
//                out.flush();
//                System.out.println("Message sent");
//
//                // Receive the reply from the server by reading from the socket input stream
//                String received = in.readLine(); // This method blocks until there  is something to read from the
//                // input stream
//                System.out.println("Message received: " + received);
//            }
//        }
//        catch(UnknownHostException e){
//            e.printStackTrace();
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }
//        finally {
//            //Close the socket
//            if(socket != null){
//                try{
//                    socket.close();
//                }
//                catch(IOException e){
//                    e.printStackTrace();
//                }
//            }
//        }

    }
}
