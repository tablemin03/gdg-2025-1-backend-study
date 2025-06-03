package com.example.todo_api.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.todo_api.common.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long signIn(Member member){
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public void deleteMember(Long memberId){
        Member member = memberRepository.findById(memberId);
        if(member == null){
            throw new RuntimeException(MEMBER_NOT_EXIST);
        }
        memberRepository.deleteById(memberId);
    }

    @Transactional
    public void updatePassword(Long memberId, String password, String newPassword){
        Member member = memberRepository.findById(memberId);
        if(member == null){
            throw new RuntimeException(MEMBER_NOT_EXIST);
        }
        boolean result = member.getPassword().equals(password);
        if(!result){
            throw new RuntimeException(NOT_YOUR_PASSWORD);
        }
        else {member.updatePassword(newPassword);}
    }

    @Transactional
    public void login(String email, String password){
        Member member = memberRepository.findByEmail(email);
        if(member == null){
            throw new RuntimeException(MEMBER_NOT_EXIST);
        }
        if(!member.getPassword().equals(password)) {
            throw new RuntimeException(NOT_YOUR_EMAIL);
        }
    }
}
