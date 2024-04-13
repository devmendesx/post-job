package br.com.mmtechnology.postjob.dto;

import br.com.mmtechnology.postjob.domain.MessageSendOptions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoWhatsImageRequest {
  private String chatId;
  private String contentType;
  private String content;
  private MessageSendOptions options;
}
