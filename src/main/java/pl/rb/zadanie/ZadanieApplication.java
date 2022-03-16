package pl.rb.zadanie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class ZadanieApplication {

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = SpringApplication.run(ZadanieApplication.class, args);
        ZadanieFacade zadanieFacade = applicationContext.getBean(ZadanieFacade.class);
        zadanieFacade.initializeConnection();
        zadanieFacade.fetchData();

    }

}
