package com.db.algotradesignal.algo;

import com.db.algotradesignal.signal.handler.SignalHandlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* This is implemented in a third-party library and we cannot change it.
* The println calls are placeholders for the actual algorithms implemented by the library.
*/
public class Algo {

    private static final Logger log = LoggerFactory.getLogger(Algo.class);
    public void doAlgo() {
        log.info("doAlgo");
    }

    public void cancelTrades() {
        log.info("cancelTrades");
    }

    public void reverse() {
        log.info("reverse");
    }

    public void submitToMarket() {
        log.info("submitToMarket");
    }

    public void performCalc() {
        log.info("performCalc");
    }

    public void setUp() {
        log.info("setUp");
    }

    public void setAlgoParam(int param, int value) {
        log.info("setAlgoParam " + param + "," + value);
    }
}
