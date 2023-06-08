package com.bnd.ecommerce.jwt;

import com.bnd.ecommerce.entity.customer.Customer;
import com.bnd.ecommerce.entity.employee.Employee;
import io.jsonwebtoken.*;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  @Value("${app.jwt.secret}")
  private String JWT_SECRET;

  @Value("${app.jwp.expiration}")
  private int JWT_EXPIRATION;

  public String generateToken(Employee employee) {
    Date now = new Date();
    Date expired = new Date(now.getTime() + JWT_EXPIRATION);
    //        generate JWT from id and email for customer
    return Jwts.builder()
        .setSubject(employee.getEmail()) // xác định rõ ràng cho ai hoặc cho cái gì
        .setIssuedAt(now)
        .setExpiration(expired)
        .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
        .setIssuer("BaoAdmin")
        .setAudience("customer")
        .compact();
  }

  public String getEmailFromJwt(String token) {
    Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();

    return claims.getSubject();
  }

  //  Validate thong tin cua JWT
  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
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
