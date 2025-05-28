package com.example.todo_api.member;

import lombok.Getter;

@Getter
public class MemberUpdateRequest {
    private Long memberId;
    private String password;
    private String newPassword;
}
