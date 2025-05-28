package com.example.todo_api.member;

import com.example.todo_api.todo.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            throw new RuntimeException("존재하지 않는 유저 ID 입니다.");
        }
        memberRepository.deleteById(memberId);
    }

    @Transactional
    public void updatePassword(Long memberId, String password, String newPassword){
        Member member = memberRepository.findById(memberId);
        if(member == null){
            throw new RuntimeException("존재하지 않는 유저 ID 입니다.");
        }
        boolean result = member.getPassword().equals(password);
        if(!result){
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        else {member.updatePassword(newPassword);}
    }

    @Transactional
    public void login(String email, String password){
        Member member = memberRepository.findByEmail(email);
        if(member == null){
            throw new RuntimeException("존재하지 않는 유저 ID 입니다.");
        }
        if(!member.getPassword().equals(password)) {
            throw new RuntimeException("이메일이나 비밀번호가 일치하지 않습니다.");
        }
    }
}
