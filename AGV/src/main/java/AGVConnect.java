import interfaces.iConnect;
import org.springframework.stereotype.Component;

@Component
public class AGVConnect implements iConnect {


    @Override
    public void connect() {
        System.out.println("bi her");
    }


}
