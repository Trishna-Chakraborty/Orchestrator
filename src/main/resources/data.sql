INSERT INTO service_host_mapping (id,service_name,host) VALUES ('1','customerservice','http://localhost:8081'),
('2','bankservice','http://localhost:8082'),
('3','orderservice','http://localhost:8083');
INSERT INTO saga_command (id,command) VALUES (1,'postOrder');
INSERT  INTO saga_step (id,end_point_name,service_name,sequence) VALUES (1,'customers','customer',1),
(2,'banks','bank',2),
(3,'orders','order',3);
INSERT INTO saga_command_saga_step_list(saga_command_id, saga_step_list_id) VALUES (1,1),(1,2),(1,3);