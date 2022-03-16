package pl.rb.zadanie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class ZadanieApplication {

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = SpringApplication.run(ZadanieApplication.class, args);
        ZadanieFacade zadanieFacade = applicationContext.getBean(ZadanieFacade.class);
        Properties appProps = zadanieFacade.getProperties();
        zadanieFacade.initializeConnection(appProps.getProperty("ip"), Integer.parseInt(appProps.getProperty("port")));
        zadanieFacade.fetchData();

    }

}
