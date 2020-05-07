package skhu.sof14.hotthink.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.dto.Point.PointChargeDto;
import skhu.sof14.hotthink.model.entity.Point;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.PointRepository;
import skhu.sof14.hotthink.repository.UserRepository;

@Service
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
}
