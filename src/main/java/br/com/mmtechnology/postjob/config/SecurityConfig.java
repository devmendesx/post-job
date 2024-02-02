package br.com.mmtechnology.postjob.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

  @Value("${mmtech.user}")
  private String user;

  @Value("${mmtech.password}")
  private String password;

  @Bean
  public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
    return new BasicAuthRequestInterceptor(this.user, this.password);
  }
}
