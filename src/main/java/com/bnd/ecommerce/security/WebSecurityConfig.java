package com.bnd.ecommerce.security;

import com.bnd.ecommerce.jwt.JwtAuthenticationFilter;
import com.bnd.ecommerce.security.employee.EmployeeDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

  private final EmployeeDetailsServiceImpl employeeDetailsService;

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  private final ObjectMapper objectMapper;

  public WebSecurityConfig(
      EmployeeDetailsServiceImpl employeeDetailsService,
      JwtAuthenticationFilter jwtAuthenticationFilter,
      ObjectMapper objectMapper) {
    this.employeeDetailsService = employeeDetailsService;
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;

    this.objectMapper = objectMapper;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @DependsOn
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(employeeDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  @DependsOn
  public void myConfigure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationProvider());
  }

  @Bean
  public SecurityFilterChain myFilterChain(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/rawUI/admin/**")
        .hasAnyAuthority("ADMIN") // only allow users with the "ADMIN" or "MANAGER"
        .antMatchers("/rawUI/manager/**")
        .hasAnyAuthority("ADMIN", "MANAGER")
        .antMatchers("/rawUI/**")
        .permitAll()
        .antMatchers("/api/customers/signin")
        .permitAll()
        .antMatchers("/api/customers/signup")
        .permitAll()
        .antMatchers("/api/customers/getInfo")
        .hasAnyAuthority("USER")
        .antMatchers("/api/orders")
        .authenticated()
        .antMatchers("/api/ordersdetail")
        .authenticated()
        .antMatchers("/api/**")
        .permitAll()
        .antMatchers("/images/**")
        .permitAll()
//        .anyRequest()
//        .authenticated()
        .and()
        .formLogin()
        .loginPage("/rawUI/login")
        .usernameParameter("email")
        .passwordParameter("password")
        .successHandler(new CustomAuthenticationSuccessHandler())
        .failureUrl("/rawUI/login?message=error")
        .permitAll()
        .and()
        .logout()
        .logoutUrl("/rawUI/logout")
        .logoutSuccessUrl("/rawUI/?logoutStatus=true")
        .permitAll()
        .and()
        .exceptionHandling()
        .accessDeniedHandler(new MyAccessDeniedHandler() {})
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    //
    http.exceptionHandling()
        .defaultAuthenticationEntryPointFor(
            new JsonAuthenticationEntryPoint(objectMapper), new AntPathRequestMatcher("/api/**"));
    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
