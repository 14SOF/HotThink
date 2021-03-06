package skhu.sof14.hotthink.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import skhu.sof14.hotthink.controller.KafkaController;
import skhu.sof14.hotthink.model.dto.message.MessageDto;
import skhu.sof14.hotthink.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MyMessageListener implements AcknowledgingMessageListener<String, MessageDto> {

    public List<MessageDto> getMessageList() {
        return messageList;
    }

    private static final List<MessageDto> messageList = new ArrayList<>();

    private static Acknowledgment lastAck;

    public static void messageCommit(){
        if(lastAck==null) return;
        lastAck.acknowledge();
        List<MessageDto> temp = new ArrayList<>(messageList);
        messageList.removeAll(temp);
        log.info("MessageListener : commit");
    }

    @Override
    public void onMessage(ConsumerRecord<String, MessageDto> data, Acknowledgment acknowledgment) {
        log.info("MessageListener received: [" + data.value()+ ", sender : "+data.value().getSender().getId()+" ]");
        messageList.add(data.value());
        lastAck = acknowledgment;
        KafkaController.sendEvents(data.value());
//        acknowledgments.add(acknowledgment);
    }
}
