package br.com.mmtechnology.postjob.dto;

import java.util.List;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateFlgProcessedRequest {
  List<Long> freeIds;
  List<Long> paidIds;
}
