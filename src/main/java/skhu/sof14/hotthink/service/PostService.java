package skhu.sof14.hotthink.service;

import io.swagger.models.Model;
import org.modelmapper.*;
import org.modelmapper.builder.ConfigurableConditionExpression;
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
import skhu.sof14.hotthink.model.entity.Comment;
import skhu.sof14.hotthink.model.entity.Like;
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
        //조회수 올림
        postRepository.updatePostByHit(id);
        //현재 게시판 엔티티
        Post entity = postRepository.findPostById(id);
        if (entity == null) return null;

        PostReadDto dto = mapper.map(entity, PostReadDto.class);
        //댓글
        int nowUser = UserService.getIdFromAuth();
        for (CommentReadDto comment : dto.getCommentList()) {
            if (comment.getUser().getId() == nowUser)
                comment.getUser().setWriter(true);
        }

        //좋아요 여부
        List<Like> likeList = entity.getLikeList();
        for (Like like : likeList) {
            if (nowUser == like.getUser().getId()) {
                dto.setUserLikeStatus(true);
                break;
            }
        }

        //댓글 좋아요 여부
        for(int i=0; i<entity.getCommentList().size(); i++){
            Comment comment = entity.getCommentList().get(i);
            likeList = comment.getLikeList();
            for (Like like : likeList) {
                if (nowUser == like.getUser().getId()) {
                    dto.getCommentList().get(i).setUserLikeStatus(true);
                    break;
                }
            }
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

    public List<PostListElementDto> findAllPage(Pagination page, String type) {
        List<Post> postList = postRepository.findAllByType(type, page);
        Type dtoListType = new TypeToken<List<PostListElementDto>>() {}.getType();
        if (type.equals("리얼")) dtoListType = new TypeToken<List<RealListElementDto>>() {}.getType();
        return mapper.map(postList, dtoListType);
    }

    @Transactional
    public void createReal(Long id, PostUpdateDto dto) {
        postRepository.createReal(id, dto.getTitle(), dto.getContent().toString(), LocalDateTime.now());
    }

    public void freeToHot(Long id){
        postRepository.updatePostToHot(id);
    }

    public static String[] getRealContent(String content){
        String[] strings = new String[8];
        for(int i=0; i<8; i++){
            int start = 0;
            int end = 0;
            switch (i){
                case 0:
                    start = content.indexOf("summary=")+8;
                    end = content.indexOf(", outline=");
                    break;
                case 1:
                    start = content.indexOf("outline=")+8;
                    end = content.indexOf(", content=");
                    break;
                case 2:
                    start = content.indexOf("content=")+8;
                    end = content.indexOf(", effect=");
                    break;
                case 3:
                    start = content.indexOf("effect=")+7;
                    end = content.indexOf(", similar=");
                    break;
                case 4:
                    start = content.indexOf("similar=")+8;
                    end = content.indexOf(", difference=");
                    break;
                case 5:
                    start = content.indexOf("difference=")+11;
                    end = content.indexOf(", patent=");
                    break;
                case 6:
                    start = content.indexOf("patent=")+7;
                    end = content.indexOf(", price=");
                    break;
                case 7:
                    start = content.indexOf("price=")+6;
                    end = content.length()-1;
                    break;
            }
            strings[i] = content.substring(start, end);
        }
        return strings;
    }
}
