package com.example.todo_api.friends;

import com.example.todo_api.member.Member;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friends")
public class FriendsController {

    private final FriendsService friendsService;

    @GetMapping("/list")
    @ApiResponse(responseCode = "404", description = "요청에 들어온 member id가 존재하지 않음")
    public ResponseEntity<List<Member>> getFriendsList(@RequestBody Long memberId){
        List<Member> friends = friendsService.readFriends(memberId);
        return ResponseEntity.ok(friends);
    }

    @PostMapping
    @ApiResponse(responseCode = "404", description = "요청에 들어온 friend id가 존재하지 않음")
    public ResponseEntity<Void> follow(@RequestBody FriendsFollowRequest request){
        friendsService.follow(request.getFollowerId(), request.getFollowId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @ApiResponse(responseCode = "404", description = "요청에 들어온 friend id가 존재하지 않음")
    public ResponseEntity<Void> unfollow(@RequestBody FriendsFollowRequest request){
        friendsService.unFollow(request.getFollowerId(), request.getFollowId());
        return ResponseEntity.noContent().build();
    }
}
