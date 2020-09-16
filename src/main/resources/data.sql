DO ' 
BEGIN 

-- role table --

        IF EXISTS
            ( SELECT 1
              FROM information_schema.tables WHERE  table_schema = ''develop'' AND table_name = ''role''
            )
        THEN
          INSERT INTO develop.role(ROLE_ID, NAME,DESCRIPTION) VALUES (1, ''SUPER_USER'',''System Owner'') ON CONFLICT (ROLE_ID) DO NOTHING;
          INSERT INTO develop.role(ROLE_ID, NAME,DESCRIPTION) VALUES (2, ''ADMIN'',''Consortium Partner'') ON CONFLICT (ROLE_ID) DO NOTHING;
          INSERT INTO develop.role(ROLE_ID, NAME,DESCRIPTION) VALUES (3, ''SUPERVISOR'',''Bank Supervisor'') ON CONFLICT (ROLE_ID) DO NOTHING;
          INSERT INTO develop.role(ROLE_ID, NAME,DESCRIPTION) VALUES (4, ''USER'',''Normal User'') ON CONFLICT (ROLE_ID) DO NOTHING;
        END IF ;

-- status table --

END';