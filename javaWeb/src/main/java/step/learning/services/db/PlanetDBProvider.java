package step.learning.services.db;

import com.google.gson.JsonParser;
import com.google.inject.Singleton;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

@Singleton
public class PlanetDBProvider implements DBProvider {
    private Connection connection;

    @Override
    public Connection getConnection() {

        if (this.connection == null) {
            JsonObject dbConfig;

            try (Reader reader = new InputStreamReader(Objects.requireNonNull(this.getClass()).getClassLoader().getResourceAsStream("db_config.json"))) {
                dbConfig = JsonParser.parseReader(reader).getAsJsonObject();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException(ex);
            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException(ex);
            }

            try {
                JsonObject planetConfig = dbConfig.get("DataProviders")
                        .getAsJsonObject().get("local").getAsJsonObject();
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                this.connection = DriverManager.getConnection(
                        planetConfig.get("url").getAsString(),
                        planetConfig.get("user").getAsString(),
                        planetConfig.get("password").getAsString()
                );
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return this.connection;
    }

}
