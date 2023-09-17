package com.db.algotradesignal.handler;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignalHandlerService {
    private static final Logger log = LoggerFactory.getLogger(SignalHandlerService.class);

    public void handleSignal(int signalId) {
        log.info("Signal [%s] received.".formatted(signalId));
    }

}
