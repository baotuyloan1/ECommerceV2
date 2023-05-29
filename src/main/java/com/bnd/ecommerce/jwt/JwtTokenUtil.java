package com.bnd.ecommerce.jwt;

import com.bnd.ecommerce.entity.customer.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {
  private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;

  @Value(value = "${app.jwt.secret}")
  private String SECRET_KEY;

  public String generateAccessToken(Customer customer) {
    return Jwts.builder()
        .setSubject(String.format("%s,%s", customer.getId(), customer.getEmail()))
        .setIssuer("BaoADMIN")
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
        .compact();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJwt(token);
      return true;
    } catch (Exception e) {
      // Xử lý token không hợp lệ hoặc hết hạn
    }
    return false;
  }

  public String getEmailFromToken(String token) {
    Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJwt(token).getBody();
    return claims.getSubject();
  }
}
