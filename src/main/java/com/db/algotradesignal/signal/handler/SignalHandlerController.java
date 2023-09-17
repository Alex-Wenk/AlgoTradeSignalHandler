package com.db.algotradesignal.signal.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("signal")
@RequiredArgsConstructor
public class SignalHandlerController {

    private final SignalHandlerService signalHandlerService;

    @PostMapping("/{signalId}")
    void executeAlgo(@PathVariable int signalId) {
        signalHandlerService.handleSignal(signalId);
    }

}
