package skhu.sof14.hotthink.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.dto.comment.CommentCreateDto;
import skhu.sof14.hotthink.model.dto.post.PostBase;
import skhu.sof14.hotthink.model.dto.user.UserBase;
import skhu.sof14.hotthink.model.entity.Comment;
import skhu.sof14.hotthink.model.entity.Post;
import skhu.sof14.hotthink.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository repository;
    @Autowired
    ModelMapper mapper;

//    //해당 게시물의 댓글들 조회
//    List<Comment> findAllByPost(PostBase post){
//        return repository.findAllByPost(mapper.map(post, Post.class));
//    }

    //생성
    public void create(Long postId, CommentCreateDto dto){
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
}
