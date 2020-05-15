package skhu.sof14.hotthink.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import skhu.sof14.hotthink.config.kafka.ConsumerConfiguration;
import skhu.sof14.hotthink.model.dto.message.AlertDto;
import skhu.sof14.hotthink.model.dto.message.MessageDto;
import skhu.sof14.hotthink.service.KafkaService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
@RestController
public class KafkaController {

    @Autowired
    KafkaService service;

    @PostMapping("send-message")
    public @ResponseBody MessageDto sendMessage(@RequestBody MessageDto dto){
        return service.sendMessage(dto);
    }

    @PostMapping("send-alert")
    public @ResponseBody
    String sendAlert(@RequestBody AlertDto dto) {
//        service.sendMessage(dto);
        return "ok";
    }

    private static ExecutorService nonBlockingService = Executors
            .newCachedThreadPool();

    private static SseEmitter emitter;

    @GetMapping("/receive-message")
    public SseEmitter handleSse() {
        log.info("세션 오픈");
        emitter = new SseEmitter(-1L);
        if (ConsumerConfiguration.messgaeListener != null) {
            List<AlertDto> alertDtos = ConsumerConfiguration.messgaeListener.getAlertDtoList();
            for (int i = 0; i < alertDtos.size(); i++) {
                sendMessageDto(alertDtos.get(i));
            }
        }
        return emitter;
    }

    @GetMapping("/commit-message")
    public @ResponseBody String commitMessage() {
        service.commitMessage();
        return "done";
    }

    private static void sendMessageDto(AlertDto dto) {
        nonBlockingService.execute(() -> {
            try {
                StringBuilder builder = new StringBuilder();
                builder.append(dto.getDateTime().getYear());
                builder.append("년 ");
                builder.append(dto.getDateTime().getMonth().getValue());
                builder.append("월 ");
                builder.append(dto.getDateTime().getDayOfMonth());
                builder.append("일");
                emitter.send(dto.getId()+"/"+dto.getType() + "/" + dto.getContent() + "/" + builder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void sendEvents(AlertDto alertDto) {
        sendMessageDto(alertDto);
    }
}
