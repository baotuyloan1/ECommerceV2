package com.bnd.ecommerce.jwt;

import com.bnd.ecommerce.dto.CustomerDto;
import io.jsonwebtoken.*;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  @Value("${app.jwt.secret}")
  private String jwtSecret;

  @Value("${app.jwp.expiration}")
  private int jwtExpiration;

  @Value("${app.jwp.issuer}")
  private String jwtIssuer;

  public String generateToken(CustomerDto customerDto) {
    Date now = new Date();
    Date expired = new Date(now.getTime() + jwtExpiration);
    return Jwts.builder()
        .setSubject(String.valueOf(customerDto.getId()))
        .setIssuedAt(now)
        .setExpiration(expired)
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .setIssuer(jwtIssuer)
        .setAudience("customer")
        .compact();
  }

  public String getIdFromJwt(String token) {
    Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token).getBody();
    return claims.getSubject();
  }

  //  Validate thong tin cua JWT
  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (MalformedJwtException ex) {
      System.err.println("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      System.err.println("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      System.err.println("Unsupported JWT Token");
    } catch (IllegalArgumentException ex) {
      System.err.println("JWTT claims String is empty");
    }
    return false;
  }
}
