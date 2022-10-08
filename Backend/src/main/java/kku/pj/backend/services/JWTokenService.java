package kku.pj.backend.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
@Qualifier("JWTokenService")
public class JWTokenService implements IJWTokenService {
    private final String secret_key;
    private final Algorithm algorithm;

    public JWTokenService(@Value("${JWT.secret_key}") String secret_key) {
        this.secret_key = secret_key;
        algorithm = Algorithm.HMAC256(secret_key.getBytes());
    }

    @Override
    public String createAccessToken(User user, String issuer) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 1000))
                .withIssuer(issuer)
                .withClaim("role", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

    @Override
    public String createRefreshToken(User user, String issuer) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    @Override
    public String getSecretKey() {
        return secret_key;
    }

    @Override
    public Algorithm getAlgorithm() {
        return algorithm;
    }

    @Override
    public DecodedJWT decode(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }
}
