package com.db.algotradesignal.signal.configuration;

import com.db.algotradesignal.algo.Algo;
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

    public void configureAlgo(Algo algo) {
        for (AlgoStepType step : algoSteps) {
            switch (step) {
                case REVERSE -> algo.reverse();
                case SET_UP -> algo.setUp();
                case SET_ALGO_PARAM -> setAlgoParams(algo);
                case PERFORM_CALC -> algo.performCalc();
                case SUBMIT_TO_MARKET -> algo.submitToMarket();
            }
        }
    }

    private void setAlgoParams(Algo algo) {
        for (AlgoParamValue algoParamValue : algoParamValues) {
            algo.setAlgoParam(algoParamValue.param(), algoParamValue.value());
        }
    }

}
