package com.example.todo_api.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signIn")
    public ResponseEntity<Void> createMember(@RequestBody MemberCreateRequest request){
        Member member = new Member(request.getEmail(), request.getPassword());
        Long memberId = memberService.signIn(member);
        return ResponseEntity.created(URI.create("/member/" + memberId)).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMember(@RequestBody Long memberId){
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<Void> updatePassword(@RequestBody MemberUpdateRequest request){
        memberService.updatePassword(request.getMemberId(), request.getPassword(), request.getNewPassword());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logIn")
    public ResponseEntity<Void> login(@RequestBody MemberCreateRequest request){
        memberService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok().build();
    }
}
