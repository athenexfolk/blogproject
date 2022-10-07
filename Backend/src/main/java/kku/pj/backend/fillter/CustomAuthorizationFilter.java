package kku.pj.backend.fillter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private final String private_key;

    @Autowired
    public CustomAuthorizationFilter(@Value("${JWT.secret_key}") String private_key) {
        this.private_key = private_key;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(! request.getServletPath().equals("/api/login")){
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                try {
                    String token = authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256(private_key.getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decoder = verifier.verify(token);

                    String username = decoder.getSubject();
                    var role = decoder.getClaim("role").asArray(String.class);
                    var authority = new ArrayList<SimpleGrantedAuthority>();
                    authority.add(new SimpleGrantedAuthority(role[0]));

                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken( decoder.getSubject(), null, authority)
                    );

//                    filterChain.doFilter(request,response);

                } catch (IllegalArgumentException e) {
                    throw new RuntimeException(e);
                } catch (JWTVerificationException e) {
                    System.out.println(e.getMessage());
                    response.sendError(FORBIDDEN.value());
                }

            }
        }

        filterChain.doFilter(request,response);
    }
}
