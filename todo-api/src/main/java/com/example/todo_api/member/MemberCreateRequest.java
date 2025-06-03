package com.example.todo_api.member;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class MemberCreateRequest {
    @Email(message = "Email 형식이어야 합니다.")
    @Length(max = 30, message = "30자 이내로 작성해야 합니다.")
    private String email;

    @Length(max = 30, message = "30자 이내로 작성해야 합니다.")
    private String password;
}
