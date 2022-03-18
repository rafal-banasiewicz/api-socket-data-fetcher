package pl.rb.task;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;

@Service
public class SocketFacade {

    private final ISocketService socketService;
    private final Environment env;

    public SocketFacade(ISocketService socketService, Environment env) {
        this.socketService = socketService;
        this.env = env;
    }

    public void initializeConnection() throws IOException {
        socketService.initializeConnection(env.getProperty("ip"), env.getProperty("port"),
                env.getProperty("login"), env.getProperty("password"), env.getProperty("symbols"));
    }

    public void fetchData() throws IOException {
        socketService.fetchData();
    }

    public Collection<Unifeed> getUnifeedCollection() {
        return socketService.getUnifeedCollection();
    }

}
