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
    private void setValue(String value){
        bootstrapServerAddress = value;
    }

    public static MyMessgaeListener messgaeListener;

    public static void startUserTopicConsumeContainer(int id){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServerAddress);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        JsonDeserializer<AlertDto> deserializer = new JsonDeserializer<>(AlertDto.class, false);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, Integer.toString(id));
        props.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());

        ContainerProperties containerProps = new ContainerProperties(Integer.toString(id));
        containerProps.setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

        messgaeListener = new MyMessgaeListener();
        containerProps.setMessageListener(messgaeListener);
        DefaultKafkaConsumerFactory<String, AlertDto> factory = new DefaultKafkaConsumerFactory<>(props, null, deserializer);

        listener = new KafkaMessageListenerContainer<>(factory, containerProps);
        log.info("리스너 시작");
        listener.start();
    }

    private static KafkaMessageListenerContainer<String, AlertDto> listener;

    public static void stopUserTopicConsumeContainer(){
        log.info("리스너 종료");
        listener.stop();
    }
}
