package skhu.sof14.hotthink.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.dto.post.PostBase;
import skhu.sof14.hotthink.model.entity.Comment;
import skhu.sof14.hotthink.model.entity.Post;
import skhu.sof14.hotthink.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository repository;
    @Autowired
    ModelMapper mapper;

    //해당 게시물의 댓글들 조회
    List<Comment> findAllByPost(PostBase post){
        return repository.findAllByPost(mapper.map(post, Post.class));
    }

}
