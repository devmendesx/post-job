package br.com.mmtechnology.postjob.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UltraMsgImageRequest {
  private String token;
  private String to;
  private String image;
  private String caption;
}
