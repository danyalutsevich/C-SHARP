package step.learning.dao;

import com.google.inject.name.Named;
import step.learning.dto.entities.User;
import step.learning.dto.models.SignUpModelForm;
import step.learning.services.auth.JwtAuth;
import step.learning.services.db.DBProvider;
import step.learning.services.hash.HashService;
import step.learning.services.random.RandomService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Singleton
public class UserDao {

    private final DBProvider dbProvider;
    private final HashService hashService;
    private final RandomService randomService;
    private final JwtAuth jwtAuthService;

    @Inject
    public UserDao(
            DBProvider dbProvider,
            @Named("Digest-hash") HashService hashService,
            RandomService random,
            JwtAuth jwtAuthService) {
        this.dbProvider = dbProvider;
        this.hashService = hashService;
        this.randomService = random;
        this.jwtAuthService = jwtAuthService;
    }

    public String login(String email, String password) {
        System.out.println(email);
        System.out.println(password);
        if (email == null || password == null) {
            return null;
        }
        String sql = "Select * From users u Where u.`email` = ?";

        try (PreparedStatement prep = dbProvider.getConnection().prepareStatement(sql)) {
            prep.setString(1, email);
            ResultSet resultSet = prep.executeQuery();
            if (resultSet.next()) {
                User user = new User(resultSet);
                System.out.println(user.email);
                System.out.println(user.password);
                System.out.println(user.salt);
                System.out.println(user.registerDate);
                String passwordHash = this.hashService.hash(password + user.salt);
                if (user.password.equals(passwordHash)) {
                    return this.jwtAuthService.encodeJwt(user);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "Wrong Data";
    }

    public String register(SignUpModelForm signUpModelForm) {

        String sql = "Insert Into users (`email`,`password`,`avatar`,`registerDate`,`salt`)" +
                " Values(?,?,?,?,?)";

        String salt = hashService.hash(randomService.randomHex(30));
        Date registerDate = new Date(System.currentTimeMillis());

        try (PreparedStatement prep = dbProvider.getConnection().prepareStatement(sql)) {
            prep.setString(1, signUpModelForm.email);
            prep.setString(2, hashService.hash(signUpModelForm.password + salt));
            prep.setString(3, signUpModelForm.avatar);
            prep.setDate(4, registerDate);
            prep.setString(5, salt);
            prep.execute();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "Registration failed";
        }

        return "Registration successfull";
    }

}
