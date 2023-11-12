package step.learning.services.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.google.inject.Singleton;

@Singleton
public class AuthService implements JwtAuth {
    public String encodeJwt(Object user) {
        return JWT.create()
                .withPayload(new Gson().toJson(user))
                .sign(Algorithm.HMAC256("s"));
    }

    public String decodeJwt(String jwt) {
        DecodedJWT decodedJWT = JWT.decode(jwt);
        return decodedJWT.getPayload();
    }
}
