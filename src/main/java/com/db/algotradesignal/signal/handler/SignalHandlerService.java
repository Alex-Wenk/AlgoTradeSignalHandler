package com.db.algotradesignal.signal.handler;

import com.db.algotradesignal.algo.Algo;
import com.db.algotradesignal.algo.AlgoFactory;
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

    private final AlgoFactory algoFactory;
    private final AlgoConfigurationService algoConfigurationService;

    public void handleSignal(int signalId) {
        log.debug("Signal [%s] received.".formatted(signalId));

        Optional<AlgoConfiguration> maybeAlgoConfigurationForSignal
                = algoConfigurationService.getAlgoConfigurationForSignal(signalId);

        Algo algo = algoFactory.getAlgo();

        if(maybeAlgoConfigurationForSignal.isPresent()) {
            log.debug("Found algo configuration for signal [%s]".formatted(signalId));
            maybeAlgoConfigurationForSignal.get().configureAlgo(algo);
        } else {
            log.debug("Did not find algo configuration for signal [%s]".formatted(signalId));
            algo.cancelTrades();
        }

        algo.doAlgo();
    }

}
