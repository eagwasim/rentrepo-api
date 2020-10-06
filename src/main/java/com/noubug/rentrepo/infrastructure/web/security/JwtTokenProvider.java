package com.noubug.rentrepo.infrastructure.web.security;


import com.noubug.rentrepo.infrastructure.exceptions.JwtTokenException;
import com.noubug.rentrepo.infrastructure.exceptions.UserNotFoundException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;
    private final Environment environmentService;

    @Inject
    public JwtTokenProvider(@Named("CustomUserDetailsService") UserDetailsService userDetailsService, Environment environmentService) {
        this.userDetailsService = userDetailsService;
        this.environmentService = environmentService;
    }

    public String createToken(Serializable userId, String username, String authKey, LocalDateTime expiration) {

        Claims claims = Jwts.claims().setSubject(userId.toString());
        claims.put("authKey", authKey);
        claims.put("username", username);

        Date expires = Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(new Date())//
                .setExpiration(expires)//
                .signWith(SignatureAlgorithm.HS256, environmentService.getProperty("security.access.token.secret"))//
                .compact();
    }

    Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        if (userDetails == null) {
            throw new UserNotFoundException();
        }
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(environmentService.getProperty("security.access.token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .get("phoneNumber", String.class);
    }

    String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(environmentService.getProperty("security.access.token.secret"))
                    .parseClaimsJws(token);
            return claims.getBody().getExpiration().after(new Date());
        } catch (JwtException | IllegalArgumentException | UsernameNotFoundException | NullPointerException e) {
            e.printStackTrace();
            throw new JwtTokenException();
        }
    }

}
