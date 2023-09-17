package com.db.algotradesignal.signal.configuration.persistence;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@Entity
@Table(
        name = "AlgoConfigParam"
        ,uniqueConstraints = {
                @UniqueConstraint(name = "uniqueSignalParam",
                        columnNames = {"signalId", "paramId"})}
)
@NoArgsConstructor
public class AlgoParamEntity {

    @ManyToOne
    @JoinColumn(name="signalId", nullable=false)
    @Id
    @EqualsAndHashCode.Include
    private AlgoConfigurationEntity algoConfiguration;
    @Id
    @EqualsAndHashCode.Include
    private int paramId;
    private int paramValue;
}
