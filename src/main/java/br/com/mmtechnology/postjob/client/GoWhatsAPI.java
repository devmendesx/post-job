package br.com.mmtechnology.postjob.client;

import br.com.mmtechnology.postjob.dto.GoWhatsImageRequest;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "gowhats-api", url = "${gowhats.baseUrl}")
public interface GoWhatsAPI {

  @PostMapping(value = "${gowhats.image}/{sessionId}", consumes = MediaType.APPLICATION_JSON_VALUE)
  void sendMessage(@PathVariable("sessionId") String sessionId,
                   GoWhatsImageRequest body,
                   @RequestHeader("x-api-key") String apiKey);
}
