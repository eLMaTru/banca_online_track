DO ' 
BEGIN 

-- role table --

        IF EXISTS
            ( SELECT 1
              FROM information_schema.tables WHERE  table_schema = ''develop'' AND table_name = ''role''
            )
        THEN
          INSERT INTO develop.role(ROLE_ID, NAME,DESCRIPTION) VALUES (1, ''ADMIN'',''System Admin'') ON CONFLICT (ROLE_ID) DO NOTHING;
          INSERT INTO develop.role(ROLE_ID, NAME,DESCRIPTION) VALUES (2, ''USER'',''Normal User'') ON CONFLICT (ROLE_ID) DO NOTHING;
          INSERT INTO develop.role(ROLE_ID, NAME,DESCRIPTION) VALUES (3, ''SUPERVISOR'',''System Supervisor'') ON CONFLICT (ROLE_ID) DO NOTHING;
          INSERT INTO develop.role(ROLE_ID, NAME,DESCRIPTION) VALUES (4, ''SUPER_USER'',''System Owner'') ON CONFLICT (ROLE_ID) DO NOTHING;
        END IF ;

-- status table --

END';