package com.bnd.ecommerce.security;

import com.bnd.ecommerce.exception.ErrorItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JsonAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final ObjectMapper objectMapper;

  public JsonAuthenticationEntryPoint(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException, ServletException {
    response.setContentType("application/json;charset=UFT-8");
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);

    ErrorItem errorItem = new ErrorItem();
    errorItem.setCode(HttpServletResponse.SC_FORBIDDEN + "");
    errorItem.setMessage("Access Denied");
    String jsonErrorResponse = objectMapper.writeValueAsString(errorItem);
    OutputStream out = response.getOutputStream();
    out.write(jsonErrorResponse.getBytes());
    //    đẩy đến client
    out.flush();
  }
}
