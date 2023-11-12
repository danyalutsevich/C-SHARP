package step.learning.services.auth;

public interface JwtAuth {
    String encodeJwt(Object jwt);
    String decodeJwt(String obj);
}
