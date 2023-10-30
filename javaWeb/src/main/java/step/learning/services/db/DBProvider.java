package step.learning.services.db;

import java.sql.Connection;

public interface DBProvider {
    Connection getConnection();

}
