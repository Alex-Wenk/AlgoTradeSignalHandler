package com.db.algotradesignal.signal.configuration;

import com.db.algotradesignal.signal.configuration.persistence.AlgoConfigurationEntity;
import com.db.algotradesignal.signal.configuration.persistence.AlgoConfigurationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AlgoConfigurationService {

    private final AlgoConfigurationRepository algoConfigurationRepository;

    public Optional<AlgoConfigurationEntity> getAlgoConfigurationForSignal(int signalId) {
        return algoConfigurationRepository.findBySignalId(signalId);
    }
}
