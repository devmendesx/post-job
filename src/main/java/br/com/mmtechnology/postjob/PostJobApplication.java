package br.com.mmtechnology.postjob;

import br.com.mmtechnology.postjob.scheduling.PostScheduling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@EnableScheduling
@EnableFeignClients(basePackages = {"br.com.mmtechnology.postjob"})
@SpringBootApplication
public class PostJobApplication {

  public static void main(String[] args) {
    SpringApplication.run(PostJobApplication.class, args);
  }

}
