package skhu.sof14.hotthink.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.dto.message.AlertDto;
import skhu.sof14.hotthink.model.entity.Comment;
import skhu.sof14.hotthink.model.entity.Like;
import skhu.sof14.hotthink.model.entity.Post;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.LikeRepository;
import skhu.sof14.hotthink.repository.UserRepository;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    LikeRepository repository;

    @Autowired
    PostService postService;

    @Autowired
    KafkaService kafkaService;



    //좋아요 생성
    public int createLike(Long id, boolean check) {
        Like entity = new Like();

        User user = new User();
        user.setId(UserService.getIdFromAuth());
        entity.setUser(user);
        List<Like> likeList;

        AlertDto alertDto = new AlertDto();
        alertDto.setId(Math.toIntExact(id));//Long 형이라서
        //true면 게시판
        if (check) {
            alertDto.setType(AlertDto.Type.LikePost);
            Post post = new Post();
            post.setId(id);
            entity.setPost(post);
            repository.save(entity);
            likeList = repository.findAllByPost(post);
            if(likeList!=null && likeList.size()>=2) postService.freeToHot(id);
        } else {//댓글
            alertDto.setType(AlertDto.Type.LikeComment);
            Comment comment = new Comment();
            comment.setId(id);
            entity.setComment(comment);
            repository.save(entity);
            likeList = repository.findAllByComment(comment);
        }
        kafkaService.sendAlert(alertDto);
        return likeList == null ? 0 : likeList.size();

    }

    public int deleteLike(Long id, boolean check) {
        User user = new User();
        user.setId(UserService.getIdFromAuth());
        List<Like> likeList;
        if (check) {
            Post post = new Post();
            post.setId(id);
            repository.deleteLikeByUserAndPost(user, post);
            likeList = repository.findAllByPost(post);
        } else {
            Comment comment = new Comment();
            comment.setId(id);
            repository.deleteLikeByUserAndComment(user, comment);
            likeList = repository.findAllByComment(comment);
        }
        return likeList == null ? 0 : likeList.size();
    }
}
