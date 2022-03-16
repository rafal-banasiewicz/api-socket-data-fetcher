package pl.rb.zadanie;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
class ZadanieService implements IZadanieService {

    private static Socket socket;
    private static BufferedWriter out;
    private final Map<String, Unifeed> unifeedMap = new ConcurrentHashMap<>();

    @Override
    public void initializeConnection(Properties appProps) throws IOException {
        socket = new Socket(appProps.getProperty("ip"), Integer.parseInt(appProps.getProperty("port")));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        sendCredentials(appProps.getProperty("login"), appProps.getProperty("password"), appProps.getProperty("symbols"));

    }

    @Override
    public void fetchData() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        skipCredentials(in);
        while (true) {
            List<String> a = getStringValues(in);
            Unifeed unifeed = Unifeed.builder()
                    .instrument(a.get(0))
                    .bid(a.get(1))
                    .ask(a.get(2))
                    .time(a.get(3))
                    .build();
            unifeedMap.put(unifeed.getInstrument(), unifeed);
        }
    }

    @Override
    public Collection<Unifeed> getUnifeedMap() {
        return unifeedMap.values();
    }

    private List<String> getStringValues(BufferedReader in) throws IOException {
        String inString = in.readLine() + " " + getCurrentTime();
        inString = inString.replace("  ", " ");
        List<String> a = List.of(inString.split(" ", 4));
        return a;
    }

    private LocalTime getCurrentTime() {
        return LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    private void skipCredentials(BufferedReader in) throws IOException {
        in.readLine();
        in.readLine();
        in.readLine();
    }

    private void sendCredentials(String login, String password, String symbols) {
        write(login);
        write(password);
        write(symbols);
    }

    private void write(String value) {
        try {
            out.write(value);
            out.newLine();
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
