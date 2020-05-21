package skhu.sof14.hotthink.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skhu.sof14.hotthink.model.entity.Follow;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.FollowRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class FollowService {

    @Autowired
    FollowRepository followRepository;

    public void createFollow(int userId) {
        Follow entity = new Follow();

        User nowUser = new User(); //현재 로그인되어있는 유저IDX
        nowUser.setId(UserService.getIdFromAuth());
        User pageUser = new User(); // 페이지 주인의 유저IDX
        pageUser.setId(userId);

        entity.setUserFollowing(pageUser);
        entity.setUserFollower(nowUser);

        followRepository.save(entity);

    }

    public void deleteFollow(int userId) {

        User nowUser = new User(); //현재 로그인되어있는 유저IDX
        nowUser.setId(UserService.getIdFromAuth());

        User pageUser = new User(); // 페이지 주인의 유저IDX
        pageUser.setId(userId);
        followRepository.deleteFollowByUserFollowerAndUserFollwing(nowUser, pageUser);
    }


    public Map<String, Object> followList(int userId){
        User pageUser = new User(); // 페이지 주인의 유저IDX
        pageUser.setId(userId);

        List<Follow> entity = followRepository.findAllByUserFollowerOrUserFollowing(pageUser, pageUser);

        List<Follow> followerList = new ArrayList<>();
        List<Follow> followingList = new ArrayList<>();

        boolean check = false;
        int nowUser = UserService.getIdFromAuth();

        for(Follow follow:entity){
            if(nowUser == follow.getUserFollower().getId()) check = true;
            if(userId == follow.getUserFollower().getId()) followerList.add(follow);
            else followingList.add(follow);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("followerList", followerList);
        map.put("followingList", followingList);
        map.put("check", check);
        return map;
    }
}
