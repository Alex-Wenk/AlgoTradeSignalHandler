package com.db.algotradesignal.signal.configuration;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@Entity
@Table(
        name = "AlgoConfigStep"
        ,uniqueConstraints = {
                @UniqueConstraint(name = "uniqueSignalStepIndex",
                        columnNames = {"signalId", "sequenceIndex"})}
)
@NoArgsConstructor
public class AlgoStepEntity {

    @ManyToOne
    @JoinColumn(name="signalId", nullable=false)
    @Id
    @EqualsAndHashCode.Include
    private AlgoConfigurationEntity algoConfiguration;
    @Id
    @EqualsAndHashCode.Include
    private int sequenceIndex;
    @Enumerated(EnumType.STRING)
    private AlgoStepType stepType;


}
