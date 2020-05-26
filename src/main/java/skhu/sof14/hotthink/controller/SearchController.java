package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import skhu.sof14.hotthink.model.dto.post.Pagination;
import skhu.sof14.hotthink.model.dto.post.SearchAllElementDto;
import skhu.sof14.hotthink.service.PostService;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    PostService postService;

    @GetMapping("/read/post/search/list")
    public String searchAllPageView(Model model, Pagination page){
        System.out.println(page);
        List<SearchAllElementDto> list = postService.findAllSearch(page);
        model.addAttribute("list", list);
        model.addAttribute("pageTitle", page.getTitle());
        int pageSize = page.getRecordCount()%10 > 0? page.getRecordCount()/10+1 : page.getRecordCount()/10;
        model.addAttribute("size", pageSize);
        model.addAttribute("page", page.getPage());
        model.addAttribute("hasNext", page.getPage()<pageSize);
        model.addAttribute("hasPre", 1<page.getPage());
        return "searchAll";

    }


}
