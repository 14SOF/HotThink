package skhu.sof14.hotthink.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;
import skhu.sof14.hotthink.config.kafka.ConsumerConfiguration;
import skhu.sof14.hotthink.config.kafka.MyMessageListener;
import skhu.sof14.hotthink.model.dto.message.AlertDto;
import skhu.sof14.hotthink.model.dto.message.MessageDto;
import skhu.sof14.hotthink.model.dto.post.PostListElementDto;
import skhu.sof14.hotthink.model.dto.user.UserPostDto;
import skhu.sof14.hotthink.model.entity.Comment;
import skhu.sof14.hotthink.model.entity.Message;
import skhu.sof14.hotthink.model.entity.Post;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.CommentRepository;
import skhu.sof14.hotthink.repository.MessageRepository;
import skhu.sof14.hotthink.repository.PostRepository;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, AlertDto> alertTemplate;

    @Autowired
    private KafkaTemplate<String, MessageDto> messageTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    ModelMapper mapper;

    public List<MessageDto> findAllByUser() {
        User user = new User();
        user.setId(UserService.getIdFromAuth());
        List<Message> list = messageRepository.findAllBySenderOrReceiver(user, user);
        Type dtoListType = new TypeToken<List<MessageDto>>() {
        }.getType();
        return mapper.map(list, dtoListType);
    }

    public MessageDto sendMessage(MessageDto dto) {
        //엔티티 저장
        dto.setDateTime(LocalDateTime.now());
        UserPostDto sender = new UserPostDto();
        sender.setId(UserService.getIdFromAuth());
        dto.setSender(sender);
        Message entity = mapper.map(dto, Message.class);
        entity = messageRepository.save(entity);

        String topic = dto.getReceiver().getId() + "_message";
        messageTemplate.send(topic, dto).addCallback(new ListenableFutureCallback<SendResult<String, MessageDto>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("메시지 보내기 실패 : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, MessageDto> result) {
                log.info("메시지 보내기 성공 [ offest = "
                        + result.getRecordMetadata().offset()
                        + ", topic = " + result.getRecordMetadata().topic() + " , data = "
                        + result.getProducerRecord().value() + " ]");
            }
        });
        return mapper.map(entity, MessageDto.class);
    }

    public List<Integer> getNewMessages() {
        List<MessageDto> messageDtoList = ConsumerConfiguration.messageListener.getMessageList();
        List<Integer> newMessagelist = new ArrayList<>();
        for (MessageDto dto : messageDtoList) {
            if(newMessagelist.contains(dto.getSender().getId())) continue;
            newMessagelist.add(dto.getSender().getId());
        }
        return newMessagelist;
    }


    public void sendAlert(AlertDto dto) {
        dto.setDateTime(LocalDateTime.now());

        int topic = -1;

        if (dto.getType() == AlertDto.Type.LikeComment) {
            Comment comment = commentRepository.findCommentById((long) dto.getId());
            String string = userService.getNickFromAuth() +
                    " 님이 " +
                    comment.getContent() +
                    " 글을 좋아합니다.";
            dto.setContent(string);
            dto.setId(Math.toIntExact(comment.getPost().getId()));
            topic = comment.getUser().getId();
        } else {
            Post post = postRepository.findPostById((long) dto.getId());
            String builder = userService.getNickFromAuth() +
                    " 님이 " +
                    post.getTitle() +
                    " 글을 좋아합니다.";
            dto.setContent(builder);
            topic = post.getUser().getId();
        }

        alertTemplate.send(Integer.toString(topic), dto).addCallback(new ListenableFutureCallback<SendResult<String, AlertDto>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("message send fail : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, AlertDto> result) {
                log.info("message send success [ offest = "
                        + result.getRecordMetadata().offset()
                        + ", topic = " + result.getRecordMetadata().topic() + " , data = "
                        + result.getProducerRecord().value() + " ]");
            }
        });
    }

}
