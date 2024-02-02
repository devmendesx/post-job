package br.com.mmtechnology.postjob.client;

import br.com.mmtechnology.postjob.dto.UltraMsgImageRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ultramsg-api", url = "${ultramsg.baseUrl}")
public interface UltraMSGClient {

  @PostMapping(value = "${ultramsg.image}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  void postMessage(UltraMsgImageRequest body);
}
