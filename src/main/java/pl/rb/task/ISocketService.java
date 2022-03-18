package pl.rb.task;

import java.io.IOException;
import java.util.Collection;

interface ISocketService {
    void initializeConnection(String ip, String port, String login, String password, String symbols) throws IOException;

    void fetchData() throws IOException;

    Collection<Unifeed> getUnifeedCollection();
}
