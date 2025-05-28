package com.example.todo_api.friends;

import com.example.todo_api.member.Member;
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
    public ResponseEntity<List<Member>> getFriendsList(@RequestBody Long memberId){
        List<Member> friends = friendsService.readFriends(memberId);
        return ResponseEntity.ok(friends);
    }

    @PostMapping
    public ResponseEntity<Void> follow(@RequestBody FriendsFollowRequest request){
        friendsService.follow(request.getFollowerId(), request.getFollowId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> unfollow(@RequestBody FriendsFollowRequest request){
        friendsService.unFollow(request.getFollowerId(), request.getFollowId());
        return ResponseEntity.noContent().build();
    }
}
