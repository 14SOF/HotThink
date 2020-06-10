package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import skhu.sof14.hotthink.model.dto.transaction.TransactionDto;
import skhu.sof14.hotthink.service.TransactionService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("/check/transaction")
    public @ResponseBody
    Map<String, Object> findByPostId(Long postId){
        TransactionDto dto = transactionService.findByPostId(postId);
        if(dto == null) return null;
        Map<String, Object> json = new HashMap<>();
        json.put("status", dto.isStatus());
        json.put("price", dto.getPrice());
        return json;
    }

    @PostMapping("/create/transaction")
    public @ResponseBody boolean createTransaction(@RequestBody TransactionDto dto){
        transactionService.save(dto);
        return true;
    }

    @PutMapping("/update/transaction")
    public @ResponseBody boolean transactionAccept(Long postId){
        transactionService.update(postId);
        return true;
    }

    @DeleteMapping("/delete/transaction")
    public @ResponseBody boolean transactionReject(Long postId){
        transactionService.delete(postId);
        return true;
    }
}
