package br.com.mmtechnology.postjob.client;

import br.com.mmtechnology.postjob.config.SecurityConfig;
import br.com.mmtechnology.postjob.dto.PostDto;
import br.com.mmtechnology.postjob.dto.UpdateFlgProcessedRequest;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mmtech-api", url = "${mmtech.url}", configuration = SecurityConfig.class)
public interface MMTechClient {

  @GetMapping(
      value = "/v1/post/{department}/{pageSize}",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  List<PostDto> getPosts(
      @PathVariable("department") String department, @PathVariable("pageSize") int pageSize);

  @PutMapping(value = "/v1/post", consumes = MediaType.APPLICATION_JSON_VALUE)
  void updateFlgProcessed(@RequestBody UpdateFlgProcessedRequest updateFlg);
}
