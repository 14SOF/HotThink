package skhu.sof14.hotthink.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;
import skhu.sof14.hotthink.config.kafka.ConsumerConfiguration;
import skhu.sof14.hotthink.model.dto.message.MessageDto;
import skhu.sof14.hotthink.model.entity.Comment;
import skhu.sof14.hotthink.model.entity.Message;
import skhu.sof14.hotthink.model.entity.Post;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.CommentRepository;
import skhu.sof14.hotthink.repository.MessageRepository;
import skhu.sof14.hotthink.repository.PostRepository;

import java.time.LocalDateTime;

@Service
@Slf4j
public class MessageService {

    @Autowired
    private KafkaTemplate<String, MessageDto> kafkaTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    public void sendMessage(MessageDto dto) {
        dto.setDateTime(LocalDateTime.now());

        int topic = -1;

        if (dto.getType() == MessageDto.Type.Message){
            Message entity = new Message();
            entity.setContent(dto.getContent());
            entity.setDateTime(LocalDateTime.now());

            User receiver = new User();
            receiver.setId(dto.getId());
            entity.setReceiver(receiver);
            topic = dto.getId();

            User sender = new User();
            sender.setId(UserService.getIdFromAuth());
            entity.setSender(sender);

            log.info(entity.toString());
            messageRepository.save(entity);
        }else if(dto.getType() == MessageDto.Type.LikeComment){
            Comment comment = commentRepository.findCommentById((long) dto.getId());
            String string = userService.getNickFromAuth() +
                    " 님이 " +
                    comment.getContent() +
                    " 글을 좋아합니다.";
            dto.setContent(string);
            dto.setId(Math.toIntExact(comment.getPost().getId()));
            topic = comment.getUser().getId();
        }else{
            Post post = postRepository.findPostById((long) dto.getId());
            String builder = userService.getNickFromAuth() +
                    " 님이 " +
                    post.getTitle() +
                    " 글을 좋아합니다.";
            dto.setContent(builder);
            topic = post.getUser().getId();
        }

        kafkaTemplate.send(Integer.toString(topic), dto).addCallback(new ListenableFutureCallback<SendResult<String, MessageDto>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("message send fail : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, MessageDto> result) {
                log.info("message send success [ offest = "
                        + result.getRecordMetadata().offset()
                        + ", topic = " + result.getRecordMetadata().topic()+" , data = "
                        + result.getProducerRecord().value() + " ]");
            }
        });
    }

    public void commitMessage(){
        if(ConsumerConfiguration.messgaeListener!=null){
            ConsumerConfiguration.messgaeListener.commit();
        }
    }
}
