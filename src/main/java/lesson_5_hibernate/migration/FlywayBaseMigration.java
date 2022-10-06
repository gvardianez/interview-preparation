package lesson_5_hibernate.migration;

import org.flywaydb.core.Flyway;

public class FlywayBaseMigration {

    private final String CONNECTION_URL;

    private final String NAME;

    private final String PASS;

    public FlywayBaseMigration(String connection_url, String name, String pass) {
        CONNECTION_URL = connection_url;
        NAME = name;
        PASS = pass;
    }

    public void migrate() {
        Flyway flyway = Flyway.configure().dataSource(CONNECTION_URL, NAME, PASS).load();
        flyway.migrate();
    }

}
