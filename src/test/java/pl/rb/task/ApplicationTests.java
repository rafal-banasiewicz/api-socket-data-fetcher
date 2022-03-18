package pl.rb.task;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApplicationTests {
    @MockBean
    SocketFacade socketFacade;

    @Before
    public void setUp() {
        List<Unifeed> unifeedCollection = new ArrayList<>();
        Unifeed unifeed = new Unifeed("BTCUSD", "40000.01", "40010.53", "19:55:18");
        unifeedCollection.add(unifeed);
        Mockito.when(socketFacade.getUnifeedCollection()).thenReturn(unifeedCollection);
    }

    @Test
    void contextLoads() {
        setUp();
        String instrument = "BTCUSD";
        String bid = "40000.01";
        String ask = "40010.53";
        String time = "19:55:18";
        Unifeed unifeed = new Unifeed(instrument, bid, ask, time);
        List<Unifeed> unifeedCollection = socketFacade.getUnifeedCollection().stream().toList();

        assertEquals(unifeedCollection.get(0).getInstrument(), instrument);
        assertEquals(unifeedCollection.get(0).getBid(), bid);
        assertEquals(unifeedCollection.get(0).getAsk(), ask);
        assertEquals(unifeedCollection.get(0).getTime(), time);
        assertEquals(unifeedCollection.get(0), unifeed);
    }

}
