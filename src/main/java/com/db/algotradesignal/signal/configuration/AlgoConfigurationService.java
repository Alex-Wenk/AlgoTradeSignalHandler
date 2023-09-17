package com.db.algotradesignal.signal.configuration;

import com.db.algotradesignal.signal.configuration.persistence.AlgoConfigurationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AlgoConfigurationService {

    private final AlgoConfigurationRepository algoConfigurationRepository;

    public Optional<AlgoConfiguration> getAlgoConfigurationForSignal(int signalId) {
        return algoConfigurationRepository.findBySignalId(signalId).map(AlgoConfiguration::fromEntity);
    }
}
