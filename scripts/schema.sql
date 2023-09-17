create table algo_config
(
    signal_id int not null
        primary key
);

create table algo_config_param
(
    param_id     int not null,
    param_value     int not null,
    signal_id int not null,
    primary key (param_id, signal_id),
    constraint FK_algo_config_param__algo_config
        foreign key (signal_id) references algo_config (signal_id)
);

create table algo_config_step
(
    sequence_index int                                                                              not null,
    step_type      enum ('PERFORM_CALC', 'REVERSE', 'SET_ALGO_PARAM', 'SET_UP', 'SUBMIT_TO_MARKET') not null,
    signal_id      int                                                                              not null,
    primary key (sequence_index, signal_id),
    constraint FK_algo_config_step__algo_config
        foreign key (signal_id) references algo_config (signal_id)
);