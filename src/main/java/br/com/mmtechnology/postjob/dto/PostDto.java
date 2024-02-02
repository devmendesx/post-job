package br.com.mmtechnology.postjob.dto;

import br.com.mmtechnology.postjob.enumerated.Department;
import java.util.List;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class PostDto {
  private Long shopId;
  private String name;
  private String whatsappId;
  private String address;
  private String imageUrl;
  private Department department;
  private String description;
  private List<String> groups;
  private boolean isPaid;
}
