package step.learning.dto.entities;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    public User(ResultSet resultSet) throws SQLException {
        this.email = resultSet.getString("email");
        this.password = resultSet.getString("password");
        this.avatar = resultSet.getString("avatar");
        this.salt = resultSet.getString("salt");
        this.registerDate = resultSet.getDate("registerDate");
    }

    @Override
    public String toString(){
        return "Email: " + this.email;
    }
    public String email;
    public String password;
    public String salt;
    public String avatar;
    public Date registerDate;

}
