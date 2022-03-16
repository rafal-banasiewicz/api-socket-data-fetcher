package pl.rb.zadanie;

import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

interface IZadanieService {
    void initializeConnection(String ip, int port, Properties appProps) throws IOException;
    void fetchData() throws IOException;
    Collection<Unifeed> getUnifeedMap();
}
