package skhu.sof14.hotthink.service;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skhu.sof14.hotthink.model.dto.comment.CommentReadDto;
import skhu.sof14.hotthink.model.dto.post.*;
import skhu.sof14.hotthink.model.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import skhu.sof14.hotthink.model.dto.post.Pagination;
import skhu.sof14.hotthink.model.dto.post.QnaCreateDto;
import skhu.sof14.hotthink.model.dto.post.QnaListElementDto;
import skhu.sof14.hotthink.model.dto.post.QnaReadDto;
import skhu.sof14.hotthink.model.dto.user.UserBase;
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
        for (int i = 0; i < entity.getCommentList().size(); i++) {
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

    public QnaReadDto findQnaById(Long id) {
        postRepository.updatePostByHit(id);
        Post entity = postRepository.findPostById(id);
        if (entity == null) return null;

        QnaReadDto dto = mapper.map(entity, QnaReadDto.class);
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
        Type dtoListType = new TypeToken<List<PostListElementDto>>() {
        }.getType();
        if (type.equals("리얼")) dtoListType = new TypeToken<List<RealListElementDto>>() {
        }.getType();
        return mapper.map(postList, dtoListType);
    }

    public List<SearchAllElementDto> findAllSearch(Pagination page) {
        List<Post> postList = postRepository.findAllByTitleContains(page);
        Type dtoListType = new TypeToken<List<SearchAllElementDto>>(){
        }.getType();
        return mapper.map(postList,dtoListType);
    }


    @Transactional
    public void createReal(Long id, PostUpdateDto dto) {
        postRepository.createReal(id, dto.getTitle(), dto.getContent().toString(), LocalDateTime.now());
    }

    public void freeToHot(Long id) {
        postRepository.updatePostToHot(id);
    }

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

    public List<QnaListElementDto> findAllQna(Pagination page) {
        List<Post> qnaList = postRepository.findAllByType("QNA", page);
        Type dtoListType = new TypeToken<List<QnaListElementDto>>() {
        }.getType();
        return mapper.map(qnaList, dtoListType);
    }

    public boolean checkOfdelete(Long id) {
        String postNick = postRepository.findPostById(id).getUser().getNick();
        String curNick = userService.getNickFromAuth();

        if (postNick.equals(curNick)) {
            return true;
        } else {
            return false;
        }
    }


    public List<PostTitleAndTypeDto> findAllByUserId(int id){
        User user = new User();
        user.setId(id);
        List<Post> posts = postRepository.findAllByUser(user);
        Type dtoListType = new TypeToken<List<PostTitleAndTypeDto>>(){}.getType();
        return mapper.map(posts, dtoListType);
    }



}
