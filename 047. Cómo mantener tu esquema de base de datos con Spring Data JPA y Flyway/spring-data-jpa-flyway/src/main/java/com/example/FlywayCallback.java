package com.example;

import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;
import org.springframework.stereotype.Component;

@Component
public class FlywayCallback implements Callback {
    @Override
    public boolean supports(Event event, Context context) {
        return event == Event.BEFORE_MIGRATE ||
                event == Event.AFTER_MIGRATE ||
                event == Event.BEFORE_EACH_MIGRATE ||
                event == Event.AFTER_EACH_MIGRATE;
    }

    @Override
    public boolean canHandleInTransaction(Event event, Context context) {
        return true;
    }

    @Override
    public void handle(Event event, Context context) {

        if (event == Event.BEFORE_MIGRATE) {
            System.out.println("BEFORE_MIGRATE");
        } else if (event == Event.AFTER_MIGRATE) {
            System.out.println("AFTER_MIGRATE");
        } else if (event == Event.BEFORE_EACH_MIGRATE) {
            System.out.println("BEFORE_EACH_MIGRATE");
            MigrationInfo migration = context.getMigrationInfo();
            System.out.println("Version: " + migration.getVersion().getVersion());


        } else if (event == Event.AFTER_EACH_MIGRATE) {
            System.out.println("AFTER_EACH_MIGRATE");
        }
    }

    @Override
    public String getCallbackName() {
        return FlywayCallback.class.getName();
    }
}
