package pl.rb.zadanie;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import java.util.Properties;

@Service
public class ZadanieFacade {

    private final IZadanieService zadanieService;

    public ZadanieFacade(IZadanieService zadanieService) {
        this.zadanieService = zadanieService;
    }

    public void initializeConnection(String ip, int port) throws IOException {
        zadanieService.initializeConnection(ip, port, getProperties());
    }

    public void fetchData() throws IOException {
        zadanieService.fetchData();
    }

    public Collection<Unifeed> getUnifeedMap() {
        return zadanieService.getUnifeedMap();
    }

    public Properties getProperties() throws IOException {
        String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        String appConfigPath = rootPath + "app.properties";
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(appConfigPath));
        return appProps;
    }
}
