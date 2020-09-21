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

        IF EXISTS
            ( SELECT 1
              FROM information_schema.tables WHERE  table_schema = ''develop'' AND table_name = ''status''
            )
        THEN
          INSERT INTO develop.status(STATUS_ID, NAME,DESCRIPTION) VALUES (1, ''ENABLED'',''Item is enabled'') ON CONFLICT (STATUS_ID) DO NOTHING;
          INSERT INTO develop.status(STATUS_ID, NAME,DESCRIPTION) VALUES (2, ''DISABLED'',''Item is disabled'') ON CONFLICT (STATUS_ID) DO NOTHING;
          INSERT INTO develop.status(STATUS_ID, NAME,DESCRIPTION) VALUES (3, ''DELETED'',''Item is deleted'') ON CONFLICT (STATUS_ID) DO NOTHING;
          INSERT INTO develop.status(STATUS_ID, NAME,DESCRIPTION) VALUES (4, ''PENDING'',''Payment is pending'') ON CONFLICT (STATUS_ID) DO NOTHING;
          INSERT INTO develop.status(STATUS_ID, NAME,DESCRIPTION) VALUES (5, ''COMPLETED'',''Payment was completed'') ON CONFLICT (STATUS_ID) DO NOTHING;
          INSERT INTO develop.status(STATUS_ID, NAME,DESCRIPTION) VALUES (6, ''CANCELED'',''Payment was canceled'') ON CONFLICT (STATUS_ID) DO NOTHING;
          INSERT INTO develop.status(STATUS_ID, NAME,DESCRIPTION) VALUES (7, ''REJECTED'',''Payment was rejected'') ON CONFLICT (STATUS_ID) DO NOTHING;
        
        END IF ;

END';