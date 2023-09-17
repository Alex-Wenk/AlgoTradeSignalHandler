package com.db.algotradesignal.signal.handler;

import com.db.algotradesignal.signal.configuration.AlgoConfiguration;
import com.db.algotradesignal.signal.configuration.persistence.AlgoConfigurationEntity;
import com.db.algotradesignal.signal.configuration.AlgoConfigurationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignalHandlerService {
    private static final Logger log = LoggerFactory.getLogger(SignalHandlerService.class);

    private final AlgoConfigurationService algoConfigurationService;

    public void handleSignal(int signalId) {
        log.info("Signal [%s] received.".formatted(signalId));

        Optional<AlgoConfiguration> maybeAlgoConfigurationForSignal
                = algoConfigurationService.getAlgoConfigurationForSignal(signalId);

        if(maybeAlgoConfigurationForSignal.isPresent()) {
            log.info("Found algo configuration for signal [%s]".formatted(signalId));
        } else {
            log.info("Did not find algo configuration for signal [%s]".formatted(signalId));
        }
    }

}
