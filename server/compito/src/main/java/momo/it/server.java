package momo.it;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class server {
    

    ServerSocket server=null;
    Socket client=null;
    String stringa_ricevuta=null;
    String stringa_biglietti=null;
    BufferedReader inDalClinet;
    DataOutputStream outVersoClient;

    ArrayList<Biglietto> LB = new ArrayList<>();


    public server() {
        try{
            server = new ServerSocket(8080);

        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("generazione fatale del server");
            System.exit(1);
        }
    }



    public Socket attendi(){
        try{
            System.out.println("il server è acceso correttamente");
                
            client = server.accept();

            inDalClinet = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());


        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("errore fatale nel server1");
            System.exit(1);
        }
        return client;
    }


    public void comunica(){
        try{

            System.out.println("nvio nomi biglietti rimasti");
            //il server out scrivendo verso il client
            outVersoClient.writeBytes(stampa());

            stringa_ricevuta = inDalClinet.readLine();
            System.out.println("il server ha ricevuto questa stringa"+stringa_ricevuta);

            
            
            outVersoClient.writeBytes(stringa_biglietti+'\n');

            System.out.println("fine elaborazione... ad victoriam");
            client.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("errore fatale nel server2");
            System.exit(1);
        }

    }


    /* 
    private ArrayList aggBiglietti(ArrayList<Biglietto> biglietti){
        ArrayList<Biglietto> B = new ArrayList<>();

        for(int i=0;i<biglietti.size();i++){
            for(int y=0;y<LB.size();y++){
                if(biglietti.get(i).getNumeroSeriale()==LB.get(i).getNumeroSeriale()){
                    B.add(LB.get(i));
                    LB.remove(i);
                }
            }
        }
        return B;
    }

*/


    public String stampa(){

        String stampa="";

        for(int i=0;i<LB.size();i++){
            stampa = stampa + LB.get(i).getPosto()+LB.get(i).getNumeroSeriale();

        }
        return stampa;


    }




    


}
