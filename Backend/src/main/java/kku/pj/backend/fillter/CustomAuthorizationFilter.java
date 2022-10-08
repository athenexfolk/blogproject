package kku.pj.backend.fillter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import kku.pj.backend.services.IJWTokenService;
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

    private final IJWTokenService jwTokenService;

    public CustomAuthorizationFilter( IJWTokenService jwTokenService) {
        this.jwTokenService = jwTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(! request.getServletPath().equals("/api/login")){
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                try {
                    String token = authorizationHeader.substring("Bearer ".length());
                    DecodedJWT decoder = jwTokenService.decode(token);

                    String username = decoder.getSubject();
                    var role = decoder.getClaim("role").asArray(String.class);
                    var authority = new ArrayList<SimpleGrantedAuthority>();
                    authority.add(new SimpleGrantedAuthority(role[0]));

                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken( username, null, authority)
                    );


                } catch (IllegalArgumentException e) {
                    throw new RuntimeException(e);
                } catch (JWTVerificationException e) {
                    response.sendError(FORBIDDEN.value());
                }

            }
        }

        filterChain.doFilter(request,response);
    }
}
