package skhu.sof14.hotthink.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import skhu.sof14.hotthink.controller.MessageController;
import skhu.sof14.hotthink.model.dto.message.MessageDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;


@Slf4j
public class MyMessgaeListener implements AcknowledgingMessageListener<String, MessageDto> {

    public List<MessageDto> getMessageDtoList() {
        return messageDtoList;
    }

    private final List<MessageDto> messageDtoList = new ArrayList<>();
    private final List<Acknowledgment> acknowledgments = new ArrayList<>();

    public void commit(){
        if(acknowledgments.size() == 0) return;
        acknowledgments.get(acknowledgments.size()-1).acknowledge();
        messageDtoList.removeAll(messageDtoList);
        acknowledgments.removeAll(acknowledgments);
    }

    @Override
    public void onMessage(ConsumerRecord<String, MessageDto> data, Acknowledgment acknowledgment) {
        log.info(this.getClass()+" received: " + data);
        LocalDateTime datetime = LocalDateTime.ofInstant(Instant.ofEpochMilli(data.timestamp()),
                TimeZone.getDefault().toZoneId());
        data.value().setDateTime(datetime);
        acknowledgments.add(acknowledgment);
        messageDtoList.add(data.value());
        MessageController.sendEvents(data.value());

    }
}
