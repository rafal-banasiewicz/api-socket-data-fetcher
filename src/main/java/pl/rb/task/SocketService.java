package pl.rb.task;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
class SocketService implements ISocketService {

    private static Socket socket;
    private static BufferedWriter out;
    private final Map<String, Unifeed> unifeedMap = new ConcurrentHashMap<>();

    @Override
    public void initializeConnection(String ip, String port, String login, String password, String symbols) throws IOException {
        socket = new Socket(ip, Integer.parseInt(port));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        sendCredentials(login, password, symbols);

    }

    @Override
    public void fetchData() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        skipCredentials(in);
        while (true) {
            List<String> feeds = getStringValues(in);
            Unifeed unifeed = Unifeed.builder()
                    .instrument(feeds.get(0))
                    .bid(feeds.get(1))
                    .ask(feeds.get(2))
                    .time(getCurrentTime().toString())
                    .build();
            unifeedMap.put(unifeed.getInstrument(), unifeed);
        }
    }

    @Override
    public Collection<Unifeed> getUnifeedCollection() {
        return unifeedMap.values();
    }

    private List<String> getStringValues(BufferedReader in) throws IOException {
        String inString = in.readLine().replaceAll(" +", " ");
        return List.of(inString.split(" ", 3));
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
