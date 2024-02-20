package br.com.mmtechnology.postjob.scheduling;

import br.com.mmtechnology.postjob.client.UltraMSGClient;
import br.com.mmtechnology.postjob.dto.UltraMsgImageRequest;

import java.time.LocalDateTime;

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

  private final UltraMSGClient ultraClient;

  @Scheduled(cron = "0 10 * * * MON-FRI")
  public void post() {
    try {
      if (isRunningHour()) {
        this.ultraClient.postMessage(UltraMsgImageRequest.builder()
                    .token(this.token)
                    .caption("ABC")
                    .image("URLDAIMAGEM")
                    .to("NUMERODETELEFONE=")
              .build());
      }
    } catch (Exception ex) {
      log.error("msg=Error on sending posts., ex={}", ex.getMessage());
    }
  }

  public boolean isRunningHour() {
    // AQUI VOCÊ SETA QUAL HORARIO SEU BOT VAI MANDAR AS MSGS
    // VAI DE 0 ate 23
    var startHour = LocalDateTime.now().withHour(8).withMinute(0).withSecond(0);
    var finalHour = LocalDateTime.now().withHour(18).withMinute(20).withSecond(0);
    log.info(
        "Verificando running hour,  now={}, start={}, final={}",
        LocalDateTime.now(),
        startHour,
        finalHour);
    var now = LocalDateTime.now();
    return (now.isAfter(startHour) && now.isBefore(finalHour));
  }
}
