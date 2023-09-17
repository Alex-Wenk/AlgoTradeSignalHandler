package com.db.algotradesignal.signal.handler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SignalHandlerControllerTest {

    @InjectMocks
    SignalHandlerController controller;
    @Mock
    SignalHandlerService signalHandlerService;

    @Test
    void executeAlgo() {
        controller.executeAlgo(1);
        Mockito.verify(signalHandlerService).handleSignal(1);
    }
}