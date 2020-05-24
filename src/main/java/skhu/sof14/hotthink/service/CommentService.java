package skhu.sof14.hotthink.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.dto.comment.CommentCreateDto;
import skhu.sof14.hotthink.model.dto.message.AlertDto;
import skhu.sof14.hotthink.model.dto.post.PostBase;
import skhu.sof14.hotthink.model.dto.user.UserBase;
import skhu.sof14.hotthink.model.entity.Comment;
import skhu.sof14.hotthink.repository.CommentRepository;

import java.time.LocalDateTime;

@Service
public class CommentService {
    @Autowired
    CommentRepository repository;
    @Autowired
    ModelMapper mapper;
    @Autowired
    KafkaService kafkaService;

    public void create(Long postId, CommentCreateDto dto){
//        AlertDto alertDto = new AlertDto();
//        alertDto.setId(Math.toIntExact(postId));//Long 형이라서
//        alertDto.setType(AlertDto.Type.Comment);
//        kafkaService.sendAlert(alertDto);

        //날짜 셋
        dto.setDateTime(LocalDateTime.now());
        //로그인한 유저 셋
        UserBase user = new UserBase();
        user.setId(UserService.getIdFromAuth());
        dto.setUser(user);
        //해당 게시물 셋
        PostBase post = new PostBase();
        post.setId(postId);
        dto.setPost(post);
        System.out.println(dto);
        Comment entity = mapper.map(dto, Comment.class);
        repository.save(entity);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
