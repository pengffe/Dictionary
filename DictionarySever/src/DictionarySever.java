import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JFrame;

public class DictionarySever extends Thread {


    private Socket clientSocket = null;
    private int numOfClients = 0;
    Dictionary dic;

    DictionarySever(Socket clientSocket, int numOfClients, Dictionary dic){
        this.clientSocket = clientSocket;
        this.numOfClients = numOfClients;
        this.dic = dic;
    }

    public void run(){
        try{
            //Get the input/output streams for reading/writing data from/to the socket
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));

            String clientMsg = null;
                while((clientMsg = in.readLine()) != null){
                    System.out.println("message from client " + numOfClients + ":" + clientMsg);
                    String meaning = dic.getMap().get(clientMsg);
                    out.write("server ack " + meaning + "\n");
                    out.flush();
                    System.out.println("response sent");
                }
        }
        catch(SocketException e){
            System.out.println("closed...");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Dictionary dic = new Dictionary();
        try{
            dic.readJsonData();
        }
        catch(IOException e){
            System.out.println("Hello World!");
        }

        ServerSocket listeningSocket = null;
        Socket clientSocket = null;

        try {
            //Create a sever socket listening on port 2048
            listeningSocket = new ServerSocket(4444);
            //counter to keep track of the number of clients
            int numOfClients = 0;

            //Listen for incoming connections for ever
            while(true){
                System.out.println("Sever lisntening on port 2048 for a connection");
                //Accept an incoming client connection request
                clientSocket = listeningSocket.accept();
                numOfClients++;
                System.out.println("Client connection number " + numOfClients + " accepted");
                System.out.println("Remote hostname: " + clientSocket.getInetAddress());
                System.out.println("Locol Port: " + clientSocket.getLocalPort());
                DictionarySever server = new DictionarySever(clientSocket, numOfClients, dic);
                server.start();

                //clientSocket.close();
            }
        }
        catch(SocketException ex){
            ex.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
