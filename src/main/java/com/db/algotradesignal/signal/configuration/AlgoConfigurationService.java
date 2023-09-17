package com.db.algotradesignal.signal.configuration;

import com.db.algotradesignal.cache.Cache;
import com.db.algotradesignal.signal.configuration.persistence.AlgoConfigurationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AlgoConfigurationService implements Cache {
    private static final Logger log = LoggerFactory.getLogger(AlgoConfigurationService.class);


    private final AlgoConfigurationRepository algoConfigurationRepository;
    private final HashMap<Integer, AlgoConfiguration> algoConfigBySignalId = new HashMap<>();

    @Override
    public void initialiseCache() {
        algoConfigurationRepository.findAll().stream()
                .map(AlgoConfiguration::fromEntity)
                .forEach(this::cacheAlgoConfiguration);
    }

    public Optional<AlgoConfiguration> getAlgoConfigurationForSignal(int signalId) {
        return Optional.ofNullable(algoConfigBySignalId.get(signalId))
                .or(() -> findAndCacheConfig(signalId));
    }

    private Optional<AlgoConfiguration> findAndCacheConfig(int signalId) {
        Optional<AlgoConfiguration> config = algoConfigurationRepository.findBySignalId(signalId)
                .map(AlgoConfiguration::fromEntity);

        config.ifPresent(this::cacheAlgoConfiguration);

        return config;
    }

    private void cacheAlgoConfiguration(AlgoConfiguration configuration) {
        log.info("Caching configuration for signal [%s]".formatted(configuration.getSignalId()));
        algoConfigBySignalId.put(configuration.getSignalId(), configuration);
    }


}
