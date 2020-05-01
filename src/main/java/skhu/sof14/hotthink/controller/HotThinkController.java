package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import skhu.sof14.hotthink.model.dto.post.Pagination;
import skhu.sof14.hotthink.model.dto.post.PostListElementDto;
import skhu.sof14.hotthink.service.PostService;

import java.util.List;

@Controller
public class HotThinkController {
    @Autowired
    PostService postService;

    // TODO: 2020-05-01 리스트 뷰 코드중복 
    @GetMapping("/read/post/hot/list")
    public String hotThinkListView(Model model, Pagination page) {
        List<PostListElementDto> list = postService.findAllPage(page,"핫");
        for(int i=1; i<=list.size(); i++){
            model.addAttribute("item"+i, list.get(i-1));
        }

        int pageSize = page.getRecordCount()%6 > 0? page.getRecordCount()/6+1 : page.getRecordCount()/6;
        model.addAttribute("size", pageSize);
        model.addAttribute("page", page.getPage());
        model.addAttribute("hasNext", page.getPage()<pageSize);
        model.addAttribute("hasPre", 1<page.getPage());
        return "hotthink_list";
    }
}
