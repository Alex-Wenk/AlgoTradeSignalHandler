package com.db.algotradesignal.signal.configuration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class AlgoConfigurationRepositoryTest {

    @Autowired
    AlgoConfigurationRepository repository;
    @Autowired
    TestEntityManager testEntityManager;
    
    @Test
    @Sql(scripts = "/scripts/dummyAlgoConfigInsert.sql")
    void findBySignalId() {
        int signalId = 1;
        Optional<AlgoConfigurationEntity> maybeConfiguration = repository.findBySignalId(signalId);
        assertTrue(maybeConfiguration.isPresent());
        AlgoConfigurationEntity foundEntity = maybeConfiguration.get();

        // check ID is as expected
        assertEquals(signalId, foundEntity.getSignalId());

        // check param loaded correctly
        assertEquals(1, foundEntity.getAlgoParams().size());
        AlgoParamEntity algoParamEntity = foundEntity.getAlgoParams().iterator().next();
        assertEquals(signalId, algoParamEntity.getAlgoConfiguration().getSignalId());
        assertEquals(1, algoParamEntity.getParamId());
        assertEquals(60, algoParamEntity.getParamValue());

        // check steps loaded
        Set<AlgoStepEntity> algoSteps = foundEntity.getAlgoSteps();
        assertEquals(5, algoSteps.size());
        assertTrue(algoSteps.stream()
                .anyMatch(stepEntityMatches(signalId, AlgoStepType.SET_UP, 0)));
        assertTrue(algoSteps.stream()
                .anyMatch(stepEntityMatches(signalId, AlgoStepType.SET_ALGO_PARAM, 1)));
        assertTrue(algoSteps.stream()
                .anyMatch(stepEntityMatches(signalId, AlgoStepType.PERFORM_CALC, 2)));
        assertTrue(algoSteps.stream()
                .anyMatch(stepEntityMatches(signalId, AlgoStepType.SUBMIT_TO_MARKET, 3)));
        assertTrue(algoSteps.stream()
                .anyMatch(stepEntityMatches(signalId, AlgoStepType.REVERSE, 4)));
    }

    private static Predicate<AlgoStepEntity> stepEntityMatches(int signalId, AlgoStepType stepType, int sequenceIndex) {
        return step -> step.getAlgoConfiguration().getSignalId() == signalId && step.getStepType() == stepType && step.getSequenceIndex() == sequenceIndex;
    }

    @Test
    void findBySignalId_notFound() {
        Optional<AlgoConfigurationEntity> bySignalId = repository.findBySignalId(1);
        assertTrue(bySignalId.isEmpty());
    }

}