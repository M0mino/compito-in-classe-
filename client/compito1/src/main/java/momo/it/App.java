package momo.it;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JsonGenerationException, JsonMappingException, IOException
    {
        System.out.println( "il client compila per adesso" );

        Client clientProva = new Client();
        clientProva.connetti();
        clientProva.comunica();

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File("messaggio.xml"), clientProva);
    }
}