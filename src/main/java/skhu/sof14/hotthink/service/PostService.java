package skhu.sof14.hotthink.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.dto.PostDto;
import skhu.sof14.hotthink.repository.PostRepository;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class PostService {
    private PostRepository postRepository;

    @Transactional
    public Long savePost(PostDto postDto) {
        return postRepository.save(postDto.toEntity()).getPost_idx();
    }
    
}