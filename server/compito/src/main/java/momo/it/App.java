package momo.it;

import java.io.File;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "per adesso parte" );

        Biglietto b1=new Biglietto("palco", 1);
        Biglietto b2=new Biglietto("palco", 2);
        Biglietto b3=new Biglietto("palco", 3);
        Biglietto b4=new Biglietto("tribuna", 4);
        Biglietto b5=new Biglietto("back stage", 5);
        Biglietto b6=new Biglietto("back stage", 6);

        server serverProva = new server();

        serverProva.LB.add(b1);
        serverProva.LB.add(b2);
        serverProva.LB.add(b3);
        serverProva.LB.add(b4);
        serverProva.LB.add(b5);
        serverProva.LB.add(b6);

        for(;;){
            serverProva.attendi();
            serverProva.comunica();

        }


       

    }
}
