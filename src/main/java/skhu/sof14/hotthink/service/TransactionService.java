package skhu.sof14.hotthink.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skhu.sof14.hotthink.model.dto.transaction.TransactionDto;
import skhu.sof14.hotthink.model.entity.Post;
import skhu.sof14.hotthink.model.entity.Transaction;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.TransactionRepository;

import java.time.LocalDateTime;

@Service
@Slf4j
public class TransactionService {
    @Autowired
    PointService pointService;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ModelMapper mapper;

    public TransactionDto findByPostId(Long id){
        Transaction entity = transactionRepository.findByPost(buildPostEntity(id));
        return entity == null? null : mapper.map(entity, TransactionDto.class);
    }

    public void update(Long id, int price){
        pointService.chargePoint(price);
        transactionRepository.queryTransactionByPost(buildPostEntity(id));
    }

    public boolean save(TransactionDto dto){
        if(!pointService.payPoint(dto.getPrice())) return false;
        User buyer = new User();
        buyer.setId(UserService.getIdFromAuth());

        Transaction entity = mapper.map(dto, Transaction.class);
        entity.setBuyer(buyer);
        entity.setDateTime(LocalDateTime.now());

        transactionRepository.save(entity);
        return true;
    }

    public void delete(Long id, int amount){
        Transaction entity = transactionRepository.findByPost(buildPostEntity(id));
        User user = entity.getBuyer();

        pointService.delete(amount, user);
        transactionRepository.deleteTransactionByPost(buildPostEntity(id));
    }

    private Post buildPostEntity(Long id){
        Post post = new Post();
        post.setId(id);
        return post;
    }
}
