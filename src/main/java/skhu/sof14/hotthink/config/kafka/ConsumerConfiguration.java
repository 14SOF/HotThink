package skhu.sof14.hotthink.config.kafka;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.serializer.JsonDeserializer;
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
    private void setValue(String value){
        bootstrapServerAddress = value;
    }

    public static MyMessgaeListener messgaeListener;

    public static void startUserTopicConsumeContainer(int id){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServerAddress);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        JsonDeserializer<MessageDto> deserializer = new JsonDeserializer<>(MessageDto.class, false);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, Integer.toString(id));

        ContainerProperties containerProps = new ContainerProperties(Integer.toString(id));
        containerProps.setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

        messgaeListener = new MyMessgaeListener();
        containerProps.setMessageListener(messgaeListener);
        DefaultKafkaConsumerFactory<String, MessageDto> factory = new DefaultKafkaConsumerFactory<>(props, null, deserializer);

        listener = new KafkaMessageListenerContainer<>(factory, containerProps);
        log.info("리스너 시작");
        listener.start();
    }

    private static KafkaMessageListenerContainer<String,MessageDto> listener;

    public static void stopUserTopicConsumeContainer(){
        log.info("리스너 종료");
        listener.stop();
    }
}
