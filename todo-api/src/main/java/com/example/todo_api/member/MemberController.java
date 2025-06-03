package com.example.todo_api.member;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
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
    @ApiResponse(responseCode = "404", description = "요청에 들어온 email이나 password가 존재하지 않음")
    public ResponseEntity<Void> createMember(@RequestBody @Valid MemberCreateRequest request){
        Member member = new Member(request.getEmail(), request.getPassword());
        Long memberId = memberService.signIn(member);
        return ResponseEntity.created(URI.create("/member/" + memberId)).build();
    }

    @DeleteMapping
    @ApiResponse(responseCode = "404", description = "요청에 들어온 member id가 존재하지 않음")
    public ResponseEntity<Void> deleteMember(@RequestBody Long memberId){
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    @ApiResponse(responseCode = "404", description = "요청에 들어온 member id가 존재하지 않음")
    public ResponseEntity<Void> updatePassword(@RequestBody @Valid MemberUpdateRequest request){
        memberService.updatePassword(request.getMemberId(), request.getPassword(), request.getNewPassword());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logIn")
    @ApiResponse(responseCode = "404", description = "요청에 들어온 email이나 password가 존재하지 않음")
    public ResponseEntity<Void> login(@RequestBody @Valid MemberCreateRequest request){
        memberService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok().build();
    }
}
