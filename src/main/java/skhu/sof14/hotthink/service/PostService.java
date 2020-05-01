package skhu.sof14.hotthink.service;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.dto.post.Pagination;
import skhu.sof14.hotthink.model.dto.post.QnaCreateDto;
import skhu.sof14.hotthink.model.dto.post.QnaListElementDto;
import skhu.sof14.hotthink.model.dto.post.QnaReadDto;
import skhu.sof14.hotthink.model.dto.user.UserBase;
import skhu.sof14.hotthink.model.dto.user.UserDetailDto;
import skhu.sof14.hotthink.model.entity.Post;
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
    UserService userService;

    public Post createQna(QnaCreateDto dto) {
        UserBase user = new UserBase();
        user.setId(UserService.getIdFromAuth());
        dto.setUser(user);
        dto.setType("QNA");
        dto.setCreateDate(LocalDateTime.now());
        return postRepository.save(mapper.map(dto, Post.class));
    }

    public List<QnaListElementDto> findAllQna(Pagination page){
        List<Post> qnaList = postRepository.findAllByType("QNA",page);
        Type dtoListType = new TypeToken<List<QnaListElementDto>>(){}.getType();
        return mapper.map(qnaList,dtoListType);
    }

    public QnaReadDto findPostById(Long id){
        postRepository.updatePostByHit(id);
        Post entity = postRepository.findPostById(id);
        if(entity == null) return null;

        QnaReadDto dto = mapper.map(entity, QnaReadDto.class);
        return dto;
    }

    // TODO: 2020-05-01  일단 nick을 비교해야함.
    public boolean checkOfdelete(Long id){
        String postNick =postRepository.findPostById(id).getUser().getNick();
        String curNick = userService.getNickFromAuth();

        if(postNick.equals(curNick)) {
            return  true;
        }else{
            return  false;
        }

    }


}