package skhu.sof14.hotthink.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.UUID;

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
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, Integer.toString(id));
        props.put(ConsumerConfig.GROUP_ID_CONFIG, Integer.toString(id));
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        String messageTopic = id + "_message";
        String alertTopic = Integer.toString(id) + "_alert";

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
//        JsonDeserializer<AlertDto> deserializer = new JsonDeserializer<>(AlertDto.class, false);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

//        alertListener = new MyAlertListener();
//            containerProps.setMessageListener(messgaeListener);
//        DefaultKafkaConsumerFactory<String, AlertDto> factory = new DefaultKafkaConsumerFactory<>(props, null, deserializer);

//        alertListener = new KafkaMessageListenerContainer<>(factory, containerProps);
//        log.info("리스너 시작");
//        alertListener.start();
    }

    private static KafkaMessageListenerContainer<String, AlertDto> alertListenerContainer;
    private static KafkaMessageListenerContainer<String, MessageDto> messageListenerContainer;

    public static void stopUserTopicConsumeContainer() {
        messageListenerContainer.stop();
        log.info("메시지 리스너 종료");
    }
}
