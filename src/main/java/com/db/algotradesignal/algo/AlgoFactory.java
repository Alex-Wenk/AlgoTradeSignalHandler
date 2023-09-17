package com.db.algotradesignal.algo;

import org.springframework.stereotype.Component;

@Component
public class AlgoFactory {

    public Algo getAlgo() {
        return new Algo();
    }
}
