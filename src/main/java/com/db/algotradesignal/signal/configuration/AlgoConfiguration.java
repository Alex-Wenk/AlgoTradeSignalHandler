package com.db.algotradesignal.signal.configuration;

import com.db.algotradesignal.signal.configuration.persistence.AlgoConfigurationEntity;
import com.db.algotradesignal.signal.configuration.persistence.AlgoParamEntity;
import com.db.algotradesignal.signal.configuration.persistence.AlgoStepEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AlgoConfiguration {

    @Getter
    private final int signalId;
    private final List<AlgoStepType> algoSteps = new ArrayList<>();
    private final List<AlgoParamValue> algoParamValues = new ArrayList<>();

    private AlgoConfiguration(int signalId) {
        this.signalId = signalId;
    }

    public static AlgoConfiguration fromEntity(AlgoConfigurationEntity entity) {
        AlgoConfiguration configuration = new AlgoConfiguration(entity.getSignalId());
        // store the steps in order
        configuration.algoSteps.addAll(
                entity.getAlgoSteps().stream()
                        .sorted(Comparator.comparingInt(AlgoStepEntity::getSequenceIndex))
                        .map(AlgoStepEntity::getStepType)
                        .toList());

        // store the params in order
        configuration.algoParamValues.addAll(
                entity.getAlgoParams().stream()
                        .sorted(Comparator.comparingInt(AlgoParamEntity::getParamId))
                        .map(AlgoParamValue::fromEntity)
                        .toList());

        return configuration;
    }


}
