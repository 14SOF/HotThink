package skhu.sof14.hotthink.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.dto.rate.RateCreateDto;
import skhu.sof14.hotthink.model.dto.rate.RateReadDto;
import skhu.sof14.hotthink.model.entity.Rate;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.RateRepository;
import skhu.sof14.hotthink.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RateService {

    public static float loginUserAvgRate;
    public static List<RateReadDto> rateDtoList = new ArrayList();

    @Autowired
    RateRepository rateRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ModelMapper mapper;

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

    public void login(int id) {
        User user = new User();
        user.setId(id);
        List<Rate> rateList = rateRepository.findAllByUser(user);

        if(rateList.size() == 0 ){
            loginUserAvgRate = 0;
            return;
        }

        float sum = 0;
        for (Rate rate : rateList) {
            sum += rate.getScore();
            rateDtoList.add(mapper.map(rate, RateReadDto.class));
        }

        loginUserAvgRate = sum / (float) rateList.size();

        log.info("User's RateAvg : " + loginUserAvgRate);
    }

    public float findRateAvg(int id){
        float avg = rateRepository.queryByUser(id);
        log.info("User's avg rate score : "+avg);
        return avg;
    }
}
