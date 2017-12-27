package server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;


import controller.Controller;
import entity.Event;
import entity.Task;


public class ServerController {
    private Controller controller;

    private Socket socket;
    private ObjectInput objectInputStream;
    private ServerSocket serverSocket;

    public ServerController()  {
        controller = new Controller();

        try {
            serverSocket = new ServerSocket(5000);
            socket = serverSocket.accept();
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void go() {
        boolean flag = true;
        int index = 0;
        try {


            while (flag) {

                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


                index = Integer.parseInt(reader.readLine());
                switch (index) {

                    case 1:
                  /*      String name = reader.readLine();
                        String description = reader.readLine();
                        LocalDateTime date = LocalDateTime.parse(reader.readLine());
                        controller.addTask(new Event(name, description, date));
                        System.out.println();*/
                        try {
                           Task task=(Event)objectInputStream.readObject();
                            System.out.println();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                }
             //   printWriter.close();
               // reader.close();
                //serverSocket.close();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void qwe() {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write("1");
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
