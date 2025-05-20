package com.example.todo_api.friends;

import com.example.todo_api.todo.Todo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendsRepository {

    @PersistenceContext
    private EntityManager em;

    // 생성
    public void save(Friends friends){
        em.persist(friends);
    }

    // 조회
    public Friends findById(Long id){
        return em.find(Friends.class, id);
    }

    public List<Friends> findAll(){
        return em.createQuery("select t from Friends as t", Friends.class).getResultList();
    }

    // 삭제
    public void deleteById(Long friend_id){
        Friends friend = em.find(Friends.class, friend_id);
        em.remove(friend);
    }
}
