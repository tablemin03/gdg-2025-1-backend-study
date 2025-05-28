package com.example.todo_api.friends;

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
    public ResponseEntity<List<Friends>> getFriendsList(@RequestBody Long memberId){
        List<Friends> friends = friendsService.readFriends(memberId);
        return ResponseEntity.ok(friends);
    }

    @PostMapping
    public ResponseEntity<Void> follow(@RequestBody FriendsFollowRequest request){
        Long followId = request.getFollowId();
        friendsService.follow(followId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> unfollow(@RequestBody Long followId){
        friendsService.unFollow(followId);
        return ResponseEntity.noContent().build();
    }
}
