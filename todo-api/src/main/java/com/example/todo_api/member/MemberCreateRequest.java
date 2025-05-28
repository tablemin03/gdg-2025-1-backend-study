package com.example.todo_api.member;

import lombok.Getter;

@Getter
public class MemberCreateRequest {
    private String email;
    private String password;
}
