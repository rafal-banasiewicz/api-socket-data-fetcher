package pl.rb.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        SocketFacade socketFacade = applicationContext.getBean(SocketFacade.class);
        socketFacade.initializeConnection();
        socketFacade.fetchData();

    }

}
