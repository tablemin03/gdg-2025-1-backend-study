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

    @JoinColumn(name = "from_member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member fromMember;

    @JoinColumn(name = "to_member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member toMember;

    public Friends(Member fromMember, Member toMember) {
        this.fromMember = fromMember;
        this.toMember = toMember;
    }
}
