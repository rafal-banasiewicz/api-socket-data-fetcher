package pl.rb.task;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
class SocketApplication {

    private final SocketFacade socketFacade;

    public SocketApplication(SocketFacade socketFacade) {
        this.socketFacade = socketFacade;
    }

    @GetMapping(value = "/getInstruments")
    public Collection<Unifeed> getInstruments() {
        return socketFacade.getUnifeedMap();
    }
}
