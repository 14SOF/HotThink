package skhu.sof14.hotthink.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.dto.comment.CommentCreateDto;
import skhu.sof14.hotthink.model.dto.message.MessageDto;
import skhu.sof14.hotthink.model.dto.post.PostBase;
import skhu.sof14.hotthink.model.dto.user.UserBase;
import skhu.sof14.hotthink.model.entity.Comment;
import skhu.sof14.hotthink.model.entity.Post;
import skhu.sof14.hotthink.repository.CommentRepository;

import java.time.LocalDateTime;

@Service
public class CommentService {
    @Autowired
    CommentRepository repository;
    @Autowired
    ModelMapper mapper;
    @Autowired
    MessageService messageService;

    //생성
    public void create(Long postId, CommentCreateDto dto){
        MessageDto messageDto = new MessageDto();
        messageDto.setId(Math.toIntExact(postId));//Long 형이라서
        messageDto.setType(MessageDto.Type.Comment);
        messageService.sendMessage(messageDto);

        dto.setDateTime(LocalDateTime.now());
        //로그인한 유저 셋
        UserBase user = new UserBase();
        user.setId(UserService.getIdFromAuth());
        dto.setUser(user);
        //해당 게시물 셋
        PostBase post = new PostBase();
        post.setId(postId);
        dto.setPost(post);
        Comment entity = mapper.map(dto, Comment.class);
        repository.save(entity);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
