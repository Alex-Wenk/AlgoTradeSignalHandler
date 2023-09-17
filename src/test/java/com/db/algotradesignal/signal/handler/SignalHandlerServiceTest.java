package com.db.algotradesignal.signal.handler;

import com.db.algotradesignal.algo.Algo;
import com.db.algotradesignal.algo.AlgoFactory;
import com.db.algotradesignal.signal.configuration.AlgoConfiguration;
import com.db.algotradesignal.signal.configuration.AlgoConfigurationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
class SignalHandlerServiceTest {

    @InjectMocks
    SignalHandlerService signalHandlerService;
    @Mock
    AlgoFactory algoFactory;
    @Mock
    AlgoConfigurationService algoConfigurationService;
    @Mock
    Algo algo;

    @BeforeEach
    void setup() {
        when (algoFactory.getAlgo()).thenReturn(algo);
    }

    @Test
    void executeAlgo_configFound() {
        // given an algo config found is found for a signal ID
        int signalId = 1;
        AlgoConfiguration algoConfiguration = mock(AlgoConfiguration.class);
        when(algoConfigurationService.getAlgoConfigurationForSignal(signalId))
                .thenReturn(Optional.of(algoConfiguration));

        // when algo is executed using the signal ID
        signalHandlerService.handleSignal(signalId);

        // then an algo is created, configured using the configuration, and executed
        InOrder algoCallOrderVerifier = inOrder(algo, algoConfiguration);
        algoCallOrderVerifier.verify(algoConfiguration).configureAlgo(algo);
        algoCallOrderVerifier.verify(algo).doAlgo();
        verifyNoMoreInteractions(algo);
        verifyNoMoreInteractions(algoConfiguration);
    }

    @Test
    void executeAlgo_configNotFound() {
        // given
        int signalId = 2;

        when(algoConfigurationService.getAlgoConfigurationForSignal(signalId))
                .thenReturn(Optional.empty());

        // when
        signalHandlerService.handleSignal(signalId);

        // then
        InOrder algoCallOrderVerifier = inOrder(algo);
        algoCallOrderVerifier.verify(algo).cancelTrades();
        algoCallOrderVerifier.verify(algo).doAlgo();
        Mockito.verifyNoMoreInteractions(algo);
    }
}