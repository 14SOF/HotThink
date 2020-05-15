package skhu.sof14.hotthink.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import skhu.sof14.hotthink.controller.KafkaController;
import skhu.sof14.hotthink.model.dto.message.AlertDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;


@Slf4j
public class MyMessgaeListener implements AcknowledgingMessageListener<String, AlertDto> {

    public List<AlertDto> getAlertDtoList() {
        return alertDtoList;
    }

    private final List<AlertDto> alertDtoList = new ArrayList<>();
    private final List<Acknowledgment> acknowledgments = new ArrayList<>();

    public void commit(){
        if(acknowledgments.size() == 0) return;
        acknowledgments.get(acknowledgments.size()-1).acknowledge();
        alertDtoList.removeAll(alertDtoList);
        acknowledgments.removeAll(acknowledgments);
    }

    @Override
    public void onMessage(ConsumerRecord<String, AlertDto> data, Acknowledgment acknowledgment) {
        log.info(this.getClass()+" received: " + data);
        LocalDateTime datetime = LocalDateTime.ofInstant(Instant.ofEpochMilli(data.timestamp()),
                TimeZone.getDefault().toZoneId());
        data.value().setDateTime(datetime);
        acknowledgments.add(acknowledgment);
        alertDtoList.add(data.value());
        KafkaController.sendEvents(data.value());

    }
}
