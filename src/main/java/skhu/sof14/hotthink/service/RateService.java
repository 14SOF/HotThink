package skhu.sof14.hotthink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.dto.rate.RateCreateDto;
import skhu.sof14.hotthink.model.entity.Rate;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.RateRepository;
import skhu.sof14.hotthink.repository.TransactionRepository;

import java.time.LocalDateTime;

@Service
public class RateService {

    @Autowired
    RateRepository rateRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public void save(RateCreateDto dto) {
        Rate entity = new Rate();
        entity.setScore(dto.getScore());
        User user = new User();
        user.setId((int) dto.getUser());
        entity.setUser(user);
        entity.setDateTime(LocalDateTime.now());
        rateRepository.save(entity);

        transactionRepository.queryTransactionByPost_User(user.getId());
    }
}
