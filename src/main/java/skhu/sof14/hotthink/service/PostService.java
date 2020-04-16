package skhu.sof14.hotthink.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.dto.PostDto;
import skhu.sof14.hotthink.repository.PostRepository;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class BoardService {
    private PostRepository postRepository;

    @Transactional
    public Long savePost(PostDto postDto) {
        return PostRepository.save(postDto.toEntity()).getPost_title();
    }
}