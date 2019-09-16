package xmlcreater;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
//
import org.json.XML;
import org.json.JSONObject;

//

public class XMLCreater {

     public static void main(String[] args) 
    {
        User customer1 = new User();
        customer1.setId(100);
        customer1.setName("Вася");

        User customer2 = new User();
        customer2.setId(101);
        customer2.setName("Петя");
        
        Users users = new Users();
        users.getUsers().add(customer1);
        users.getUsers().add(customer2);
        try {
            String xmlFileName = "C:\\MyData\\file3.xml";
            File file = new File(xmlFileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(users, file);
            jaxbMarshaller.marshal(users, System.out);
//переделываем в json
//проверили результат работы
            //далее могу использовать созданный файл
            Path path = file.toPath();
            String tmps = Files.lines(path).
                    collect(Collectors.joining("\n"));
            JSONObject xmlToJson = XML.toJSONObject(tmps);
            String jsonString = xmlToJson.toString();
            System.out.println(jsonString);
//
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException ex) {
              ex.printStackTrace();
         }
        
    }
    
}
