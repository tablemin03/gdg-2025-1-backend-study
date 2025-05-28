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
    public void follow(Long fromId, Long toId) {
        Member fromMem = memberRepository.findById(fromId);
        Member toMem = memberRepository.findById(toId);
        Friends friend = new Friends(fromMem, toMem);
        friendsRepository.save(friend);
    }

    @Transactional(readOnly = true)
    public List<Member> readFriends(Long fromId){
        return friendsRepository.findAll(fromId);
    }

    @Transactional
    public void unFollow(Long from, Long to) {
        Friends friend = friendsRepository.findByFromAndTo(from, to)
                .orElseThrow(() -> new RuntimeException("팔로우 관계를 찾을 수 없습니다."));
        if(friend == null){
            throw new RuntimeException("존재하지 않는 유저 ID 입니다.");
        }
        friendsRepository.deleteById(friend.getId());
    }
}
