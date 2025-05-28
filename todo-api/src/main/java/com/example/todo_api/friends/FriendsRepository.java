package com.example.todo_api.friends;

import com.example.todo_api.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FriendsRepository {

    @PersistenceContext
    private EntityManager em;

    // 생성
    public void save(Friends friends){
        em.persist(friends);
    }

    public Optional<Friends> findByFromAndTo(Long fromId, Long toId) {
        return em.createQuery("SELECT f FROM Friends f WHERE f.fromMember.id = :from AND f.toMember.id = :to", Friends.class)
                .setParameter("from", fromId)
                .setParameter("to", toId)
                .getResultStream()
                .findFirst();
    }

    public List<Member> findAll(Long fromId){
        return em.createQuery("SELECT f.toMember FROM Friends f WHERE f.fromMember.id = :fromId", Member.class)
                .setParameter("fromId", fromId)
                .getResultList();
    }

    // 삭제
    public void deleteById(Long friend_id){
        Friends friend = em.find(Friends.class, friend_id);
        em.remove(friend);
    }
}
