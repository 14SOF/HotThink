package skhu.sof14.hotthink.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import skhu.sof14.hotthink.config.kafka.ConsumerConfiguration;
import skhu.sof14.hotthink.config.kafka.MyAlertListener;
import skhu.sof14.hotthink.config.kafka.MyMessageListener;
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

    //메시지 보내기
    @PostMapping("send-message")
    public @ResponseBody MessageDto sendMessage(@RequestBody MessageDto dto){
        return service.sendMessage(dto);
    }

    private static SseEmitter emitter;

    private static final ExecutorService nonBlockingService = Executors.newCachedThreadPool();

    @GetMapping("/sse")
    public SseEmitter handleSse() {
        emitter = new SseEmitter(-1L);
        log.info("세션 오픈 ="+emitter.toString());
        if (ConsumerConfiguration.messageListener != null ) {
            if(ConsumerConfiguration.messageListener.getMessageList().size()>0){
                sendEvents("{\"sender\":\"씨발\"}");
            }
        }
        if(ConsumerConfiguration.alertListener != null){
            List<AlertDto> alertDtoList = ConsumerConfiguration.alertListener.getAlertDtoList();
            if(alertDtoList.size()>0){
                for(AlertDto dto : alertDtoList){
                    sendEvents(dto);
                }
            }
        }
        return emitter;
    }



//    @PostMapping("send-alert")
//    public @ResponseBody
//    String sendAlert(@RequestBody AlertDto dto) {
//        service.sendAlert(dto);
//        return "ok";
//    }

    public static void sendEvents(String event){
        nonBlockingService.execute(()->{
            try {
                emitter.send(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void sendEvents(MessageDto messageDto){
        nonBlockingService.execute(()->{
            try {
                emitter.send(messageDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void sendEvents(AlertDto alertDto){
        nonBlockingService.execute(()->{
            try {
                emitter.send(alertDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @GetMapping("/commit-message")
    public void commitMessage(){
        MyMessageListener.messageCommit();
    }

    //메시지 커밋
    @GetMapping("/commit-alert")
    public void commitAlert() {
        MyAlertListener.commit();
    }

    public static void sseClose(){
        emitter.complete();
    }
}
