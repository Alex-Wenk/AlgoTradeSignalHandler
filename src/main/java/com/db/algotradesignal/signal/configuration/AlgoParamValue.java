package com.db.algotradesignal.signal.configuration;


import com.db.algotradesignal.signal.configuration.persistence.AlgoParamEntity;

public record AlgoParamValue(int param, int value) {

    public static AlgoParamValue fromEntity(AlgoParamEntity entity) {
        return new AlgoParamValue(entity.getParamId(), entity.getParamValue());
    }
}
