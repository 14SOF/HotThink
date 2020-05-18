package skhu.sof14.hotthink.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.dto.overlap.OverlapDto;
import skhu.sof14.hotthink.model.entity.Overlap;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.OverlapRepository;
import skhu.sof14.hotthink.repository.UserRepository;

import java.util.List;

@Service
public class OverlapService {

    @Autowired
    OverlapRepository overlapRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    public Overlap overlapReport(OverlapDto dto, Long postId){
        User user = new User();
        user= userRepository.findUserById(UserService.getIdFromAuth());
        dto.setUser(user);
        dto.setPostId(postId);


        return overlapRepository.save(mapper.map(dto,Overlap.class));
    }

    //표절신고중복체크
    public boolean overlapCheckService(Long postId){
        User curUser = userRepository.findUserById(UserService.getIdFromAuth());
        List<Overlap> entity = overlapRepository.findOverlapByPostId(postId);

        for(int i=0; i<entity.size(); i++){
            for(Overlap overlap : entity){
                User reporterUser = entity.get(i).getUser();
                if(reporterUser == curUser) {
                    return false;
                }
            }
        }
        return true;
    }


}
