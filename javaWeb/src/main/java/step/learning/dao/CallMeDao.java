package step.learning.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import step.learning.dto.models.CallMeModel;
import step.learning.services.db.DBProvider;

import java.sql.PreparedStatement;

@Singleton
public class CallMeDao {

    private final DBProvider dbProvider;

    @Inject
    public CallMeDao(DBProvider dbProvider) {
        this.dbProvider = dbProvider;
    }

    public void create(CallMeModel model) {

        String sql = "INSERT INTO callme (NAME,phoneNumber,email) VALUES(?,?,?)";

        try (PreparedStatement prep = dbProvider.getConnection().prepareStatement(sql)) {
            System.out.println("Model: " + model.name + model.email + model.phoneNumber);
            prep.setString(1, model.name);
            prep.setString(2, model.phoneNumber);
            prep.setString(3, model.email);
            prep.execute();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


}
