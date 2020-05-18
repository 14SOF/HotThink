package skhu.sof14.hotthink.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import skhu.sof14.hotthink.model.dto.message.AlertDto;
import skhu.sof14.hotthink.model.dto.message.MessageDto;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@Slf4j
public class ConsumerConfiguration {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private static String bootstrapServerAddress;

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private void setValue(String value) {
        bootstrapServerAddress = value;
    }

    public static MyAlertListener alertListener;
    public static MyMessageListener messageListener;

    public static void startUserTopicConsumeContainer(int id) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServerAddress);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, id+"_message");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, Integer.toString(id));
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        String messageTopic = id + "_message";
        String alertTopic = id + "_alert";

        ContainerProperties containerProps = new ContainerProperties(messageTopic);
        containerProps.setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

        JsonDeserializer<MessageDto> messageDeserializer = new JsonDeserializer<>(MessageDto.class, false);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, messageDeserializer);

        messageListener = new MyMessageListener();
        containerProps.setMessageListener(messageListener);
        DefaultKafkaConsumerFactory<String, MessageDto> messageFactory = new DefaultKafkaConsumerFactory<>(props, null, messageDeserializer);

        messageListenerContainer = new KafkaMessageListenerContainer<>(messageFactory, containerProps);
        messageListenerContainer.start();
        log.info("메시지 리스너 시작");

        props.remove(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG);
        props.remove(ConsumerConfig.CLIENT_ID_CONFIG);
        JsonDeserializer<AlertDto> alertDeserializer = new JsonDeserializer<>(AlertDto.class, false);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, alertDeserializer);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, id+"_alert");

        alertListener = new MyAlertListener();
        DefaultKafkaConsumerFactory<String, AlertDto> alertFactory = new DefaultKafkaConsumerFactory<>(props, null, alertDeserializer);

        containerProps = new ContainerProperties(alertTopic);
        containerProps.setMessageListener(alertListener);
        containerProps.setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        alertListenerContainer = new KafkaMessageListenerContainer<>(alertFactory, containerProps);
        log.info("알림 리스너 시작");
        alertListenerContainer.start();
    }

    private static KafkaMessageListenerContainer<String, AlertDto> alertListenerContainer;
    private static KafkaMessageListenerContainer<String, MessageDto> messageListenerContainer;

    public static void stopUserTopicConsumeContainer() {
        messageListenerContainer.stop();
        alertListenerContainer.stop();
        messageListener = null;
        alertListener = null;
        log.info("메시지 리스너, 알림 리스너 종료");
    }
}
