INSERT INTO algo_config (signal_id) VALUES (1);
INSERT INTO algo_config (signal_id) VALUES (2);
INSERT INTO algo_config (signal_id) VALUES (3);

INSERT INTO algo_config_param (param_id, param_value, signal_id) VALUES (1, 60, 1);
INSERT INTO algo_config_param (param_id, param_value, signal_id) VALUES (1, 80, 2);
INSERT INTO algo_config_param (param_id, param_value, signal_id) VALUES (1, 90, 3);
INSERT INTO algo_config_param (param_id, param_value, signal_id) VALUES (2, 15, 3);

INSERT INTO algo_config_step (sequence_index, step_type, signal_id) VALUES (0, 'SET_UP', 1);
INSERT INTO algo_config_step (sequence_index, step_type, signal_id) VALUES (1, 'SET_ALGO_PARAM', 1);
INSERT INTO algo_config_step (sequence_index, step_type, signal_id) VALUES (2, 'PERFORM_CALC', 1);
INSERT INTO algo_config_step (sequence_index, step_type, signal_id) VALUES (3, 'SUBMIT_TO_MARKET', 1);
INSERT INTO algo_config_step (sequence_index, step_type, signal_id) VALUES (0, 'REVERSE', 2);
INSERT INTO algo_config_step (sequence_index, step_type, signal_id) VALUES (1, 'SET_ALGO_PARAM', 2);
INSERT INTO algo_config_step (sequence_index, step_type, signal_id) VALUES (2, 'SUBMIT_TO_MARKET', 2);
INSERT INTO algo_config_step (sequence_index, step_type, signal_id) VALUES (0, 'SET_ALGO_PARAM', 3);
INSERT INTO algo_config_step (sequence_index, step_type, signal_id) VALUES (1, 'PERFORM_CALC', 3);
INSERT INTO algo_config_step (sequence_index, step_type, signal_id) VALUES (2, 'SUBMIT_TO_MARKET', 3);
