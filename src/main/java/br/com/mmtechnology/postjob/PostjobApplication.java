package br.com.mmtechnology.postjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients(basePackages = {"br.com.mmtechnology.postjob"})
@SpringBootApplication
public class PostjobApplication {

  public static void main(String[] args) {
    SpringApplication.run(PostjobApplication.class, args);
  }
}
