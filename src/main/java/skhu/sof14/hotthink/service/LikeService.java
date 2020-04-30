package skhu.sof14.hotthink.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.dto.post.PostBase;
import skhu.sof14.hotthink.model.dto.user.UserBase;
import skhu.sof14.hotthink.model.entity.Like;
import skhu.sof14.hotthink.model.entity.Post;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.LikeRepository;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    LikeRepository repository;

    @Autowired
    ModelMapper mapper;

    public int createPostLike(Long id) {
        Post post = new Post();
        post.setId(id);

        Like entity = new Like();

        // TODO: 2020-04-30 시큐리티에서 받아올 것 수정바람
        User user = new User();
        user.setId(28);

        entity.setPost(post);
        entity.setUser(user);

        repository.save(entity);

        return repository.findAllByPost(post).size();
    }

    public int deletePostLike(Long id) {
        Post post = new Post();
        post.setId(id);
        User user = new User();
        user.setId(28);

        repository.deleteLikeByUserAndPost(user, post);

        List<Like> list = repository.findAllByPost(post);
        if (list == null) return 0;
        else return list.size();
    }
}
