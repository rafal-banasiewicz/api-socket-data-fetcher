package pl.rb.zadanie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
class ZadanieController {

    private final ZadanieFacade zadanieFacade;

    public ZadanieController(ZadanieFacade zadanieFacade) {
        this.zadanieFacade = zadanieFacade;
    }

    @GetMapping(value = "/getInstruments")
    public Collection<Unifeed> getInstruments() {
        return zadanieFacade.getUnifeedMap();
    }
}
