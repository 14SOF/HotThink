package skhu.sof14.hotthink.service;

import io.swagger.models.Model;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skhu.sof14.hotthink.model.dto.comment.CommentReadDto;
import skhu.sof14.hotthink.model.dto.post.*;
import skhu.sof14.hotthink.model.entity.Post;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.PostRepository;
import skhu.sof14.hotthink.repository.UserRepository;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    UserRepository userRepository;

    public PostReadDto findPostById(Long id) {
        postRepository.updatePostByHit(id);
        Post entity = postRepository.findPostById(id);
        if(entity==null) return null;
        PostReadDto dto = mapper.map(entity, PostReadDto.class);
        for(CommentReadDto comment : dto.getCommentList()){
            if (comment.getUser().getId() == UserService.getIdFromAuth())
                comment.getUser().setWriter(true);
        }
        return dto;
    }

    public Long createFree(PostCreateDto dto) {
        User writer = userRepository.findUserById(UserService.getIdFromAuth());
        dto.setUser(writer);
        dto.setType("프리");
        Post entity = mapper.map(dto, Post.class);
        return postRepository.save(entity).getId();
    }

    public List<PostListElementDto> findAllFree(Pagination page) {
        List<Post> postList = postRepository.findAllByType("프리", page);
        Type dtoListType = new TypeToken<List<PostListElementDto>>(){}.getType();
        return mapper.map(postList, dtoListType);
    }

    @Transactional
    public void createReal(Long id, PostUpdateDto dto){
        postRepository.createReal(id, dto.getTitle(), dto.getContent().toString(), LocalDateTime.now());
    }
}
