package br.com.mmtechnology.postjob.scheduling;

import br.com.mmtechnology.postjob.client.GoWhatsAPI;
import br.com.mmtechnology.postjob.domain.MessageSendOptions;
import br.com.mmtechnology.postjob.dto.GoWhatsImageRequest;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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

  private final GoWhatsAPI goWhatsAPI;
  private final String sessionId = "abc";

  @Value("${gowhats.token}")
  private String token;

  @Scheduled(cron = "5 10 * * * *")
  public void sendSignal() {
    try {
      try {
        var message =
            GoWhatsImageRequest.builder()
                .chatId("120363261327179395@g.us")
                .contentType("MessageMediaFromURL")
                .content("http://opinavotoapi/v1/storage/image/signal.png")
                .options(
                    MessageSendOptions.builder().linkPreview(true).caption(buildSignal()).build())
                .build();
        this.goWhatsAPI.sendMessage(sessionId, message, this.token);
        var wait = new Random().nextInt(40000) + 20000;
        log.info("Sinal enviado com sucesso!");
        Thread.sleep(wait);
      } catch (Exception ex) {
        log.error("msg=Error on sending posts., ex={}", ex.getMessage());
      }
    } catch (Exception ex) {
      log.error("msg=Error on sending posts., ex={}", ex.getMessage());
    }
  }

  String buildSignal() {
    var numbers = numbers();
    var now = LocalTime.now();
    var hour = now.getHour();

    return String.format(
        """

                ⏰ *MINUTOS PAGANTES* ⏰

                ✅ %02d:%s ✅ %02d:%s ✅ %02d:%s
                ✅ %02d:%s ✅ %02d:%s ✅ %02d:%s
                ✅ %02d:%s ✅ %02d:%s ✅ %02d:%s
                ✅ %02d:%s ✅ %02d:%s ✅ %02d:%s

                🚨 *BANCAS LUCRANDO* 🚨
                🥇R$60,00
                🥈R$50,00
                🥉R$30,00

                ✅ *FORTUNE TIGER* 🐯
                ✅ *FORTUNE RABBIT* 🐰
                ✅ *FORTUNE MOUSE*  🐀
                ✅ *FORTUNE OX* 🐂

                🥇 *PLATAFORMA INDICADA*: 🥇
                https://tinyurl.com/opaganhei

                _Deposite a partir de R$20 pro sinal valer._

                ☘️🍀 Boa sorte! ☘️🍀
                """,
        hour,
        numbers.get(0),
        hour,
        numbers.get(1),
        hour,
        numbers.get(2),
        hour,
        numbers.get(3),
        hour,
        numbers.get(4),
        hour,
        numbers.get(5),
        hour,
        numbers.get(6),
        hour,
        numbers.get(7),
        hour,
        numbers.get(8),
        hour,
        numbers.get(9),
        hour,
        numbers.get(10),
        hour,
        numbers.get(11));
  }

  List<String> numbers() {
    List<String> numbersList = generateUniqueNumbersList();
    Collections.sort(numbersList);
    return numbersList;
  }

  public static List<String> generateUniqueNumbersList() {
    Random random = new Random();
    List<String> numbersList = new ArrayList<>();
    var number = 0;
    while (numbersList.size() < 12) {
      if (numbersList.size() > 10) {
        number = random.nextInt(40, 60);
      } else {
        number = random.nextInt(60); // Generate a random number between 0 and 59
      }

      // Format the number to have leading zeros if necessary
      String formattedNumber = String.format("%02d", number);

      // Ensure the generated number is not already in the list
      if (!numbersList.contains(formattedNumber)) {
        numbersList.add(formattedNumber);
      }
    }

    return numbersList;
  }
}
