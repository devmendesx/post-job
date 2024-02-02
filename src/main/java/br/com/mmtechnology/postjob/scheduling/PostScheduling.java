package br.com.mmtechnology.postjob.scheduling;

import br.com.mmtechnology.postjob.client.MMTechClient;
import br.com.mmtechnology.postjob.client.UltraMSGClient;
import br.com.mmtechnology.postjob.dto.PostDto;
import br.com.mmtechnology.postjob.dto.UltraMsgImageRequest;
import br.com.mmtechnology.postjob.dto.UpdateFlgProcessedRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostScheduling {

  @Value("${ultramsg.token}")
  private String token;

  @Value("${mmtech.image-download}")
  private String imageDownloadUrl;

  @Value("${mmtech.url}")
  private String baseUrl;

  private final LocalDateTime startHour =
      LocalDateTime.now().withHour(8).withMinute(0).withSecond(0);
  private final LocalDateTime finalHour =
      LocalDateTime.now().withHour(18).withMinute(0).withSecond(0);

  private final MMTechClient mmClient;
  private final UltraMSGClient ultraClient;

  @Scheduled(fixedDelay = 600000)
  public void postsFitness() {
    try {
      if (!isRunningHour()) return;
      var posts = mmClient.getPosts("FITNESS", 1);
      log.info("Enviando novos posts, posts={}", posts);
      posts.forEach(
          post -> {
            var imageUrl = this.baseUrl + this.imageDownloadUrl + post.getImageUrl();
            var message =
                this.buildMessage(
                    post.getName(),
                    post.getDepartment().name(),
                    post.getDescription(),
                    post.getAddress(),
                    post.getWhatsappId());
            post.getGroups()
                .forEach(
                    group -> {
                      var body =
                          UltraMsgImageRequest.builder()
                              .token(this.token)
                              .to("120363197289116102@g.us")
                              .image(imageUrl)
                              .caption(message)
                              .build();
                      this.ultraClient.postMessage(body);
                      System.out.println("Message sent! Group -> " + group);
                    });
          });
      this.setFlgProcessed(posts);
    } catch (Exception ex) {
      log.error("msg=Error on sending posts., ex={}", ex.getMessage());
    }
  }

  @Scheduled(fixedDelay = 600000)
  public void postsKids() {
    try {
      if (!isRunningHour()) return;
      var posts = mmClient.getPosts("INFANTIL", 1);
      log.info("Enviando novos posts, posts={}", posts);
      posts.forEach(
          post -> {
            var imageUrl = this.baseUrl + this.imageDownloadUrl + post.getImageUrl();
            var message =
                this.buildMessage(
                    post.getName(),
                    post.getDepartment().name(),
                    post.getDescription(),
                    post.getAddress(),
                    post.getWhatsappId());
            post.getGroups()
                .forEach(
                    group -> {
                      var body =
                          UltraMsgImageRequest.builder()
                              .token(this.token)
                              .to("120363197289116102@g.us")
                              .image(imageUrl)
                              .caption(message)
                              .build();
                      this.ultraClient.postMessage(body);
                      System.out.println("Message sent! Group -> " + group);
                    });
          });

      this.setFlgProcessed(posts);
    } catch (Exception ex) {
      log.error("msg=Error on sending posts., ex={}", ex.getMessage());
    }
  }

  private void setFlgProcessed(List<PostDto> posts) {
    var freeIds =
        posts.stream()
            .filter(post -> !post.isPaid())
            .map(PostDto::getShopId)
            .collect(Collectors.toList());
    var paidIds =
        posts.stream().filter(PostDto::isPaid).map(PostDto::getShopId).collect(Collectors.toList());
    var updateRequest =
        UpdateFlgProcessedRequest.builder().freeIds(freeIds).paidIds(paidIds).build();
    log.info("update={}", updateRequest.toString());
    this.mmClient.updateFlgProcessed(updateRequest);
  }

  public String buildMessage(
      String name, String department, String description, String address, String whatsappId) {
    var link = "https://divulgacao.feiradesantacruz.com.br/r/?f=" + whatsappId;
    return String.format(
        "*%s* | %s \n%s \n\n 📲 Link WhatsApp Vendedor\n%s \n\n 📍 Endereço da Fábrica 👇🏻👇🏻 \n%s",
        name, department, description, link, address);
  }

  public boolean isRunningHour() {
    var now = LocalDateTime.now();
    return now.isAfter(startHour) && now.isBefore(finalHour);
  }
}
