package kku.pj.backend.utills;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.User;

public interface IJWTokenService {

    String createAccessToken(User user, String issuer);

    String createRefreshToken(User user, String issuer);

    String getSecretKey();

    Algorithm getAlgorithm();

    DecodedJWT decode(String token);
}
