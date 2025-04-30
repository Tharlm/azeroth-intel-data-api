INSERT INTO tb_region (id, lb_name, lb_tag)
VALUES (1, 'North America', 'US');
INSERT INTO tb_region (id, lb_name, lb_tag)
VALUES (2, 'Korea', 'KR');
INSERT INTO tb_region (id, lb_name, lb_tag)
VALUES (3, 'Europe', 'EU');

-- parameter type
INSERT INTO public.tb_parameter_type (lb_key, lb_name, lb_type)
VALUES ('REA', 'Normal', 'NORMAL');
INSERT INTO public.tb_parameter_type (lb_key, lb_name, lb_type)
VALUES ('REA', 'Roleplaying', 'RP');
INSERT INTO public.tb_parameter_type (lb_key, lb_name, lb_type)
VALUES ('CRS', 'Up', 'UP');
INSERT INTO public.tb_parameter_type (lb_key, lb_name, lb_type)
VALUES ('CRP', 'Low', 'LOW');

-- connected realm 12 and 531
INSERT INTO public.tb_connected_realm (id, bo_queue, id_param_population, id_param_status, bo_active, dt_last_update,
                                       id_region)
VALUES (12, false, 4, 3, true, '2024-01-06 19:56:19.121324', 1);
INSERT INTO public.tb_connected_realm (id, bo_queue, id_param_population, id_param_status, bo_active, dt_last_update,
                                       id_region)
VALUES (531, false, 4, 3, true, '2024-01-06 19:56:27.957058', 3);

-- 3 US realms from connected realm  12
INSERT INTO public.tb_realm (id, id_region, lb_name, lb_category, lb_locale, lb_timezone, lb_slug, bo_tournament,
                             id_param, id_connected_realm, dt_last_update, bo_active)
VALUES (12, 1, 'Silver Hand', 'United States', 'enUS', 'America/Los_Angeles', 'silver-hand', false, 2, 12,
        '2024-01-06 19:56:19.156403', true);
INSERT INTO public.tb_realm (id, id_region, lb_name, lb_category, lb_locale, lb_timezone, lb_slug, bo_tournament,
                             id_param, id_connected_realm, dt_last_update, bo_active)
VALUES (1154, 1, 'Thorium Brotherhood', 'United States', 'enUS', 'America/Los_Angeles', 'thorium-brotherhood', false,
        2, 12, '2024-01-06 19:56:19.191700', true);
INSERT INTO public.tb_realm (id, id_region, lb_name, lb_category, lb_locale, lb_timezone, lb_slug, bo_tournament,
                             id_param, id_connected_realm, dt_last_update, bo_active)
VALUES (1370, 1, 'Farstriders', 'United States', 'enUS', 'America/Los_Angeles', 'farstriders', false, 2, 12,
        '2024-01-06 19:56:19.225869', true);

-- 5 EU realms from connected realm 531
INSERT INTO public.tb_realm (id, id_region, lb_name, lb_category, lb_locale, lb_timezone, lb_slug, bo_tournament,
                             id_param, id_connected_realm, dt_last_update, bo_active)
VALUES (531, 3, 'Dethecus', 'German', 'deDE', 'Europe/Paris', 'dethecus', false, 1, 531, '2024-01-06 19:56:27.992088',
        true);
INSERT INTO public.tb_realm (id, id_region, lb_name, lb_category, lb_locale, lb_timezone, lb_slug, bo_tournament,
                             id_param, id_connected_realm, dt_last_update, bo_active)
VALUES (605, 3, 'Theradras', 'German', 'deDE', 'Europe/Paris', 'theradras', false, 1, 531,
        '2024-01-06 19:56:28.027090', true);
INSERT INTO public.tb_realm (id, id_region, lb_name, lb_category, lb_locale, lb_timezone, lb_slug, bo_tournament,
                             id_param, id_connected_realm, dt_last_update, bo_active)
VALUES (610, 3, 'Onyxia', 'German', 'deDE', 'Europe/Paris', 'onyxia', false, 1, 531, '2024-01-06 19:56:28.062091',
        true);
INSERT INTO public.tb_realm (id, id_region, lb_name, lb_category, lb_locale, lb_timezone, lb_slug, bo_tournament,
                             id_param, id_connected_realm, dt_last_update, bo_active)
VALUES (1319, 3, 'Mug''thol', 'German', 'deDE', 'Europe/Paris', 'mugthol', false, 1, 531, '2024-01-06 19:56:28.096156',
        true);
INSERT INTO public.tb_realm (id, id_region, lb_name, lb_category, lb_locale, lb_timezone, lb_slug, bo_tournament,
                             id_param, id_connected_realm, dt_last_update, bo_active)
VALUES (615, 3, 'Terrordar', 'German', 'deDE', 'Europe/Paris', 'terrordar', false, 1, 531,
        '2024-01-06 19:56:28.131156', true);

