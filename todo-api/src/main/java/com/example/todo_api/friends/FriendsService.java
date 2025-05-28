package com.example.todo_api.friends;

import com.example.todo_api.member.Member;
import com.example.todo_api.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendsService {
    private final FriendsRepository friendsRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void follow(Long friendId){
        Friends friend = friendsRepository.findById(friendId);
        friendsRepository.save(friend);
    }

    @Transactional(readOnly = true)
    public List<Friends> readFriends(Long friendId){
        Friends friend = friendsRepository.findById(friendId);
        if(friend == null){
            throw new RuntimeException("존재하지 않는 유저 ID 입니다.");
        }
        return friendsRepository.findAll();
    }

    @Transactional
    public void unFollow(Long followId){
        Friends friend = friendsRepository.findById(followId);
        if(friend == null){
            throw new RuntimeException("존재하지 않는 유저 ID 입니다.");
        }
        friendsRepository.deleteById(followId);
    }
}
