package skhu.sof14.hotthink.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import skhu.sof14.hotthink.config.kafka.ConsumerConfiguration;
import skhu.sof14.hotthink.model.dto.message.MessageDto;
import skhu.sof14.hotthink.service.MessageService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
@RestController
public class MessageController {

    @Autowired
    MessageService service;

    @PostMapping("send-message")
    public @ResponseBody
    String sendMessage(@RequestBody MessageDto dto) {
        service.sendMessage(dto);
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
            List<MessageDto> messageDtos = ConsumerConfiguration.messgaeListener.getMessageDtoList();
            for (int i = 0; i < messageDtos.size(); i++) {
                sendMessageDto(messageDtos.get(i));
            }
        }
        return emitter;
    }

    @GetMapping("/commit-message")
    public @ResponseBody String commitMessage() {
        service.commitMessage();
        return "done";
    }

    private static void sendMessageDto(MessageDto dto) {
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

    public static void sendEvents(MessageDto messageDto) {
        sendMessageDto(messageDto);
    }
}
