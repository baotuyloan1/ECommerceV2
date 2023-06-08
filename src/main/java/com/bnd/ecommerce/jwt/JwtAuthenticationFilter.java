package com.bnd.ecommerce.jwt;

import com.bnd.ecommerce.security.EmployeeDetailsServiceImpl;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  @Autowired private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private EmployeeDetailsServiceImpl employeeDetailsService;

  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String jwt = getJwtFromRequest(request);
      if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
        String email = jwtTokenProvider.getEmailFromJwt(jwt);
        UserDetails userDetails = employeeDetailsService.loadUserByUsername(email);
        if (userDetails != null) {
          //      neu nguoi dung hop le set thong tin cho security context
            UsernamePasswordAuthenticationToken authentication =
              new UsernamePasswordAuthenticationToken(
                  userDetails, null, userDetails.getAuthorities());

          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
    } catch (Exception ex) {
      System.err.println("fail on set user authentication " + ex);
    }
    filterChain.doFilter(request,response);
  }
}
