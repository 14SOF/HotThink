package skhu.sof14.hotthink.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import skhu.sof14.hotthink.controller.KafkaController;
import skhu.sof14.hotthink.model.dto.message.AlertDto;

import java.util.ArrayList;
import java.util.List;


@Slf4j
public class MyAlertListener implements AcknowledgingMessageListener<String, AlertDto> {
    public MyAlertListener() {
        alertDtoList = new ArrayList<>();
        lastAck = null;
    }

    public List<AlertDto> getAlertDtoList() {
        return alertDtoList;
    }

    private static List<AlertDto> alertDtoList;
    private static Acknowledgment lastAck;

    public static void commit(){
        if(lastAck==null) return;
        lastAck.acknowledge();
        List<AlertDto> temp = new ArrayList<>(alertDtoList);
        alertDtoList.removeAll(temp);
    }

    @Override
    public void onMessage(ConsumerRecord<String, AlertDto> data, Acknowledgment acknowledgment) {
        log.info(this.getClass()+" received: " + data);
        lastAck = acknowledgment;
        alertDtoList.add(data.value());
        KafkaController.sendEvents(data.value());
    }
}
