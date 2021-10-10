
import java.io.*;
import java.net.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Francesco
 */
public class MultiClient {
     String nomeServer ="localhost";  //nome del server
    int portaServer = 6789;     //porta del server
    Socket miosocket;       //socket
    BufferedReader tastiera;        //input tastiera
    String stringaUtente;       // stringa dal utente
    String stringaRicevutaDalServer;        //stringa dal server
    DataOutputStream outVersoServer;        //output verso server
    BufferedReader inDalServer;             //input verso server
    
   
    
    public void comunica()
    {
        for(;;)
        try     //leggo una riga
        {
            System.out.println("4 ... utente, inserisci la striniga da trasmettere al server:"+'\n');
            stringaUtente = tastiera.readLine();
            //la spedisco al server
            System.out.println("5 ... invio la stringa al server e attendo ...");
            outVersoServer.writeBytes( stringaUtente+'\n');
            //risposta del server
            stringaRicevutaDalServer=inDalServer.readLine();
            System.out.println("7 ... risposta dal server "+'\n'+stringaRicevutaDalServer );
            if(stringaUtente.equals("FINE"))
            {
                System.out.println("8 CLIENT: termina elaborazione e chiude connessione");
                miosocket.close();
                break;
            }
        }    
        catch   (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicaione col server!");
            System.exit(1);
        }
    }
    
    public Socket connetti(){
        System.out.println("2 CLIENT partito in esecuzione...");
        try {
            //input da tastiera
            tastiera = new BufferedReader (new InputStreamReader(System.in));
            // miosocket = new Socket(InetAddress.getLocalHost(), 6789);
            miosocket = new Socket(nomeServer, portaServer);
            //associo due oggetti al socket per effettuare la scrittura e la lettura 
            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
        } catch (UnknownHostException e) {
            
            System.err.println("Host sconosciuto");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione!");
            System.exit(1);
        }
        return miosocket;
    }
    
}
