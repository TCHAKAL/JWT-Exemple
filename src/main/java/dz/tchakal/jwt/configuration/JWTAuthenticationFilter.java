package dz.tchakal.jwt.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import dz.tchakal.jwt.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static dz.tchakal.jwt.Util.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl(SIGN_UP_URL+"/login");//sets the default login URL
    }

    //this function runs when the user tries to log in to our application
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User userCreds = new ObjectMapper().readValue(request.getInputStream(),User.class);
            return authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      userCreds.getUsername(),
                      userCreds.getPassword(),
                      new ArrayList<>()// the authorities (roles)
              )
            );

        }catch (IOException e){
            throw new RuntimeException();
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(((User) authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRATION_TIME))//15 min
                .sign(Algorithm.HMAC512(SECRET.getBytes()));// the authorities (roles)

        String body = ((User) authResult.getPrincipal()).getUsername()+" "+token;
        response.getWriter().write(body);
        response.getWriter().flush();
    }
}
