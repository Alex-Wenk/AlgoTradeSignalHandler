package com.db.algotradesignal.signal.configuration;

import com.db.algotradesignal.algo.Algo;
import com.db.algotradesignal.signal.configuration.persistence.AlgoConfigurationEntity;
import com.db.algotradesignal.signal.configuration.persistence.AlgoParamEntity;
import com.db.algotradesignal.signal.configuration.persistence.AlgoStepEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
class AlgoConfigurationTest {

    @Mock
    Algo algo;

    @Test
    void configureAlgo() {
        int signalId = 1;
        AlgoConfigurationEntity algoConfigurationEntity = new AlgoConfigurationEntity(signalId);

        AlgoStepEntity step1 = new AlgoStepEntity(algoConfigurationEntity, 0, AlgoStepType.SET_UP);
        AlgoStepEntity step2 = new AlgoStepEntity(algoConfigurationEntity, 1, AlgoStepType.SET_ALGO_PARAM);
        AlgoStepEntity step3 = new AlgoStepEntity(algoConfigurationEntity, 2, AlgoStepType.PERFORM_CALC);
        AlgoStepEntity step4 = new AlgoStepEntity(algoConfigurationEntity, 3, AlgoStepType.REVERSE);
        AlgoStepEntity step5 = new AlgoStepEntity(algoConfigurationEntity, 5, AlgoStepType.SUBMIT_TO_MARKET);
        algoConfigurationEntity.getAlgoSteps().addAll(List.of(step1,step2,step3,step4, step5));

        AlgoParamEntity param1 = new AlgoParamEntity(algoConfigurationEntity, 1, 60);
        AlgoParamEntity param2 = new AlgoParamEntity(algoConfigurationEntity, 2, 90);
        algoConfigurationEntity.getAlgoParams().addAll(List.of(param1,param2));

        AlgoConfiguration algoConfig = AlgoConfiguration.fromEntity(algoConfigurationEntity);

        algoConfig.configureAlgo(algo);

        InOrder algoCallOrderVerifier = inOrder(algo);

        algoCallOrderVerifier.verify(algo).setUp();
        algoCallOrderVerifier.verify(algo).setAlgoParam(1,60);
        algoCallOrderVerifier.verify(algo).setAlgoParam(2,90);
        algoCallOrderVerifier.verify(algo).performCalc();
        algoCallOrderVerifier.verify(algo).reverse();
        algoCallOrderVerifier.verify(algo).submitToMarket();
        Mockito.verifyNoMoreInteractions(algo);
    }
}