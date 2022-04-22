import interfaces.iConnect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class etellerande {


    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        //scan i package navn efter komponeter
        context.scan("");
        //skal man kalde
        context.refresh();

        for (Map.Entry<String, iConnect> iConnectEntry: context.getBeansOfType(iConnect.class).entrySet()) {
            System.out.println(iConnectEntry);
            System.out.println(iConnectEntry.getKey());
            System.out.println(iConnectEntry.getValue());
            iConnectEntry.getValue().connect();

        }

        // kan lave andre for loops til andre interfaces kig på Jan

        // kan også lave andre metoder som Jan gør (render og draw?)

    }

}
