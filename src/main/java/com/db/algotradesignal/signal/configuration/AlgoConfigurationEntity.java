package com.db.algotradesignal.signal.configuration;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "AlgoConfig")
@Getter
@NoArgsConstructor
public class AlgoConfigurationEntity {

    @Id
    private int signalId;
    @OneToMany(mappedBy = "algoConfiguration", cascade = CascadeType.ALL)
    private final Set<AlgoStepEntity> algoSteps = new HashSet<>();
    @OneToMany(mappedBy = "algoConfiguration", cascade = CascadeType.ALL)
    private final Set<AlgoParamEntity> algoParams = new HashSet<>();
}
