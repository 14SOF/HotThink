package skhu.sof14.hotthink.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import skhu.sof14.hotthink.config.kafka.ConsumerConfiguration;
import skhu.sof14.hotthink.config.kafka.MyMessageListener;
import skhu.sof14.hotthink.model.dto.message.AlertDto;
import skhu.sof14.hotthink.model.dto.message.MessageDto;
import skhu.sof14.hotthink.service.KafkaService;
import skhu.sof14.hotthink.utils.SseUtil;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
@RestController
public class KafkaController {

    @Autowired
    KafkaService service;

    //메시지 보내기
    @PostMapping("send-message")
    public @ResponseBody MessageDto sendMessage(@RequestBody MessageDto dto){
        return service.sendMessage(dto);
    }

    private static SseEmitter emitter;

    private static final ExecutorService nonBlockingService = Executors.newCachedThreadPool();

    @GetMapping("/sse")
    public SseEmitter handleSse() {
        log.info("세션 오픈");
        emitter = new SseEmitter(-1L);
        if (ConsumerConfiguration.messageListener != null ) {
            if(MyMessageListener.check){
                sendEvents("Message");
            }
//            for(MessageDto message : ConsumerConfiguration.messageListener.getMessageList()){
//                sendEvents(message);
//            }
//            List<AlertDto> alertDtos = ConsumerConfiguration.messgaeListener.getAlertDtoList();
//            for (int i = 0; i < alertDtos.size(); i++) {
//                sendMessageDto(alertDtos.get(i));
//            }
        }
        return emitter;
    }



    @PostMapping("send-alert")
    public @ResponseBody
    String sendAlert(@RequestBody AlertDto dto) {
//        service.sendMessage(dto);
        return "ok";
    }

    public static void sendEvents(String alarm){
        nonBlockingService.execute(()->{
            try {
                emitter.send(alarm);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void sendEvents(MessageDto messageDto){
        nonBlockingService.execute(()->{
            try {
                emitter.send(SseUtil.messageDtoToString(messageDto));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



//    private static void sendMessageDto(AlertDto dto) {
//        nonBlockingService.execute(() -> {
//            try {
//                StringBuilder builder = new StringBuilder();
//                builder.append(dto.getDateTime().getYear());
//                builder.append("년 ");
//                builder.append(dto.getDateTime().getMonth().getValue());
//                builder.append("월 ");
//                builder.append(dto.getDateTime().getDayOfMonth());
//                builder.append("일");
//                emitter.send(dto.getId()+"/"+dto.getType() + "/" + dto.getContent() + "/" + builder.toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }

//    public static void sendEvents(AlertDto alertDto) {
//        sendMessageDto(alertDto);
//    }

    //메시지 커밋
    @GetMapping("/commit-message")
    public void commitMessage() {
        MyMessageListener.messageCommit();
    }
}
