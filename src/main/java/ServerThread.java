/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Francesco
 */

import java.net.*;

import java.io.*;

public class ServerThread extends Thread{
    
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public ServerThread(Socket socket) {
        
        this.client = socket;
    }
    
    
    public void run()
    {
        try {
            comunica();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    public void comunica () throws Exception{
        
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());
        for(;;){
            stringaRicevuta = inDalClient.readLine();
            if(stringaRicevuta == null || stringaRicevuta.equals("FINE")){
                outVersoClient.writeBytes(stringaRicevuta+"(--> server in chiusura...)"+'\n');
                System.out.println("Echo sul server in chiusura :"+stringaRicevuta);
                break;
            }
            else
            {
                outVersoClient.writeBytes(stringaRicevuta+"(ricevuta e ritrasmessa)"+'\n');
                System.out.println("6 Echo sul server :"+ stringaRicevuta);
            }
        }
        
        outVersoClient.close();
        inDalClient.close();
        System.out.println("9 Chiusura socket"+client);
        client.close();
    }
    
    
    
}
