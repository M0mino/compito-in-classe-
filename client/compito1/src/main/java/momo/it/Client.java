package momo.it;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Client {
    

    String nomeServer = "localhost";
    int portaServer = 8080;
    Socket miosocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDaSer;
    DataOutputStream outVersoSer;
    DataOutputStream outVersoSer2;
    BufferedReader inDalSer;

    String numerobiglietti;


    ArrayList<Mess> LBiglietti = new ArrayList<>();
   


    public Socket connetti() {
        System.out.println("client in esecuzione...");
        try{

            tastiera=new BufferedReader(new InputStreamReader(System.in));

            miosocket=new Socket(nomeServer,portaServer);
            outVersoSer=new DataOutputStream(miosocket.getOutputStream());
            inDalSer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));

        }catch(UnknownHostException e){
            System.err.println("errore fatale nel client");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante connessione");
            System.exit(1);
        }
        return miosocket;
    }


    public void comunica(){
        try{

            //step 1: devo inviare al server un messaggio con un array vuoto

            Mess m = new Mess();

            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(m);
            outVersoSer.writeBytes(xml + "\n");

            // riceverò dal server la lista dei biglietti disponibili

            String xmlRicevuto = inDalSer.readLine();

            Mess messRicevuto = xmlMapper.readValue(xmlRicevuto, Mess.class);

            // stampo la lista ricevuta

            for(int i=0;i<6;i++){
                System.out.println(messRicevuto+"\n");

            }

            //prendo in input da tastiera l'id del biglietto che voglio comprare
            
            //creo una nuova lista di biglietti con dentro solo il biglietto con l'id inserito dall'utente (oppure prendo il primo biglietto)
            
            System.out.println("inserire il numero seriale dei biglietti da comprare:");
            acquista();

            //invio al server la nuova lista



            //ricevo dal server la lista dei biglietti venduti

            System.out.println("biglieti acquistati:"+'\n'+stringaRicevutaDaSer);


            //disconnetto

            System.out.println("chiusa canale comunicaione, addio");
            miosocket.close();



            
           

            System.out.println("sto inviando la lista dei biglietti al server");
            outVersoSer.writeBytes(numerobiglietti+'\n');

            
            
        
            
        }
    
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante latrasmissione");
            System.exit(1);
        }
    }

    private String acquista() throws IOException{
        String momoString="";
        String stringaDiSupporto="";
    
        for(;;){
            stringaDiSupporto=tastiera.readLine(); 
            if(stringaDiSupporto.equals("fine")){
                return momoString;
               
                
            }
            else{
            momoString=momoString+","+stringaDiSupporto;  
            }

        }
       
    }


}
