package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import skhu.sof14.hotthink.model.dto.comment.CommentCreateDto;
import skhu.sof14.hotthink.service.CommentService;

@Controller
public class CommentController {
    @Autowired
    CommentService service;

    @PostMapping("/create/comment")
    public ResponseEntity<String> createComment(@RequestParam Long id, @RequestBody CommentCreateDto dto){
        service.create(id, dto);
        return ResponseEntity.ok().build();
    }
}
