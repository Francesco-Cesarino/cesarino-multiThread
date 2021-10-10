
import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Francesco
 */
public class MultiServer {

    public void start(){
        try {
            ServerSocket serverSocket = new ServerSocket(6789);
            for(;;)
            {
                System.out.println("1 Server in attesa...");
                Socket socket = serverSocket.accept();
                System.out.println("3 Server socket "+socket);
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
            }
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server !");
            System.exit(1);
        }
    }
    
}
