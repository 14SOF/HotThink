package skhu.sof14.hotthink.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skhu.sof14.hotthink.model.dto.Point.PointChargeDto;
import skhu.sof14.hotthink.model.entity.Point;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.PointRepository;
import skhu.sof14.hotthink.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class PointService {

    @Autowired
    PointRepository pointRepository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    UserRepository userRepository;

    public Point chargePoint(PointChargeDto dto, int id){
        User user = userRepository.findUserById(id);
        dto.setUser(user);
        return pointRepository.save(mapper.map(dto, Point.class));
    }

    public Point chargePoint(int amount){
        User user = new User();
        user.setId(UserService.getIdFromAuth());

        Point point = new Point();
        point.setAmount((long) amount);
        point.setCreateDate(LocalDateTime.now());
        point.setUser(user);

        return pointRepository.save(point);
    }

    public boolean payPoint(int amount){
        if(amountSum() < amount) return false;
        User user = new User();
        user.setId(UserService.getIdFromAuth());

        Point point = new Point();
        point.setAmount((long) -amount);
        point.setCreateDate(LocalDateTime.now());
        point.setUser(user);

        pointRepository.save(point);
        return true;
    }

    public void delete(int amount, User user){
        pointRepository.deleteByAmountAndUser((long) -amount, user);
    }

    public List<Point> ChargeList(){
        User user = userRepository.findUserById(UserService.getIdFromAuth());
        return pointRepository.findAllByUser(user);
    }

    public Long amountSum(){
        User user = userRepository.findUserById(UserService.getIdFromAuth());
        return pointRepository.amountSum(user);
    }

}
