package com.db.algotradesignal.signal.configuration;

import com.db.algotradesignal.signal.configuration.persistence.AlgoConfigurationEntity;
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


}
