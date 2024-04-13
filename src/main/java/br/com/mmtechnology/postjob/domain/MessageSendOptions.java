package br.com.mmtechnology.postjob.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class MessageSendOptions {
  private String caption;
  @Builder.Default private boolean linkPreview = true;
}
