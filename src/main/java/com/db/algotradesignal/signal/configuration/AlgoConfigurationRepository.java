package com.db.algotradesignal.signal.configuration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlgoConfigurationRepository  extends JpaRepository<AlgoConfigurationEntity, Integer> {

    Optional<AlgoConfigurationEntity> findBySignalId(int signalId);

}
