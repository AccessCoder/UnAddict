package de.unaddict.backend.security;

import de.unaddict.backend.components.MongoUserDetailsService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final Log LOG = LogFactory.getLog(JwtAuthFilter.class);

    final JWTUtils jwtUtils;
    final MongoUserDetailsService userService;


    public JwtAuthFilter(JWTUtils jwtUtils, MongoUserDetailsService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter) throws ServletException, IOException {
        final String token = getToken(request);
        if (token != null) try {
            final String username = jwtUtils.extractUserName(token);
            if ((username != null)
                    && (SecurityContextHolder.getContext().getAuthentication() == null)) {
                //Lade User aus DB
                final UserDetails userDetails = userService.loadUserByUsername(username);
                if (jwtUtils.validateToken(token, userDetails.getUsername())){
                    //Info an Spring, das Anmeldung korrekt ist!
                    final UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            LOG.warn("Error while parsing Token!", e);
        }
        filter.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            return null;
        }else {
            return authHeader.replace("Bearer", "").trim();
        }
    }
}

