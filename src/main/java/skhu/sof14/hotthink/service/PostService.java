package skhu.sof14.hotthink.service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.dto.post.QnaCreateDto;
import skhu.sof14.hotthink.model.dto.user.UserBase;
import skhu.sof14.hotthink.model.entity.Post;
import skhu.sof14.hotthink.repository.PostRepository;
import skhu.sof14.hotthink.repository.UserRepository;

import java.time.LocalDateTime;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper mapper;


    @Autowired
    UserService userService;

    public Post createQna(QnaCreateDto dto) {
        UserBase user = new UserBase();
        user.setId(UserService.getIdFromAuth());
        dto.setUser(user);
        dto.setType("QNA");
        dto.setCreateDate(LocalDateTime.now());
        return postRepository.save(mapper.map(dto, Post.class));
    }

}