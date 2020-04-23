package skhu.sof14.hotthink.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.dto.PostDto;
import skhu.sof14.hotthink.model.entity.PostEntity;
import skhu.sof14.hotthink.repository.PostRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class PostService {
    private PostRepository postRepository;

    @Transactional
    public Long savePost(PostDto postDto) {
        return postRepository.save(postDto.toEntity()).getPostIdx();
    }

    @Transactional
    public List<PostDto> getPostlist() {
        List<PostEntity> postEntities = postRepository.findAll();
        List<PostDto> postDtoList = new ArrayList<>();

        for ( PostEntity postEntity : postEntities) {
            PostDto postDTO = PostDto.builder()
                    .postIdx(postEntity.getPostIdx())
                    .postTitle(postEntity.getPostTitle())
                    .postContent(postEntity.getPostContent())
                    .postHit(postEntity.getPostHit())
                    .postLike(postEntity.getPostLike())
                    .creatTime(postEntity.getCreatTime())
                    .postType(postEntity.getPostType())
                    .userIdx(postEntity.getUserIdx())
                    .build();
            postDtoList.add(postDTO);
        }

        return postDtoList;
    }
}