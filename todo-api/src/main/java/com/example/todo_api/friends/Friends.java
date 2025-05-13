package com.example.todo_api.friends;

import com.example.todo_api.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private Long id;

    @JoinColumn(name = "memeber_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Friends(Member member) {
        this.member = member;
    }
}
