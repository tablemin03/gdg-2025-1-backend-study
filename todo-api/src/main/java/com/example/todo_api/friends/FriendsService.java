package com.example.todo_api.friends;

import com.example.todo_api.member.Member;
import com.example.todo_api.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.todo_api.common.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class FriendsService {
    private final FriendsRepository friendsRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void follow(Long fromId, Long toId) {
        Member fromMem = memberRepository.findById(fromId);
        Member toMem = memberRepository.findById(toId);
        if(fromMem == null)
            throw new RuntimeException(FROM_MEMBER_NOT_EXIST);
        if(toMem == null)
            throw new RuntimeException(TO_MEMBER_NOT_EXIST);
        Friends friend = new Friends(fromMem, toMem);
        friendsRepository.save(friend);
    }

    @Transactional(readOnly = true)
    public List<Member> readFriends(Long fromId){
        Member fromMem = memberRepository.findById(fromId);
        if(fromMem == null)
            throw new RuntimeException(FROM_MEMBER_NOT_EXIST);
        return friendsRepository.findAll(fromId);
    }

    @Transactional
    public void unFollow(Long from, Long to) {
        Friends friend = friendsRepository.findByFromAndTo(from, to)
                .orElseThrow(() -> new RuntimeException(LIST_NOT_EXIST));
        if(friend == null){
            throw new RuntimeException(FRIEND_NOT_EXIST);
        }
        friendsRepository.deleteById(friend.getId());
    }
}
