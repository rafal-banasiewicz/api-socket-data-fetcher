package pl.rb.zadanie;

import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

interface IZadanieService {
    void initializeConnection(Properties appProps) throws IOException;
    void fetchData() throws IOException;
    Collection<Unifeed> getUnifeedMap();
}
