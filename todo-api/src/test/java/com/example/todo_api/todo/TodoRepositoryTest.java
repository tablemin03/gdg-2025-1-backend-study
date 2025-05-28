package com.example.todo_api.todo;

import com.example.todo_api.member.Member;
import com.example.todo_api.member.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    void todoCreateTest(){
        Todo todo = new Todo ("todo content",null);

        todoRepository.save(todo);

        assertThat(todo.getId()).isNotNull();
    }

    @Test
    @Transactional
    void TodoFindOneByIdTest(){
        Todo todo = new Todo ("todo content",null);
        todoRepository.save(todo);
        Todo findTodo = todoRepository.findById(todo.getId());

        assertThat(findTodo.getId()).isEqualTo(todo.getId());
    }

    @Test
    @Transactional
    void todoFindAllTest(){
        Todo todo1 = new Todo ("todo content1", null);
        Todo todo2 = new Todo ("todo content2", null);
        Todo todo3 = new Todo ("todo content3",null);
        todoRepository.save(todo1);
        todoRepository.save(todo2);
        todoRepository.save(todo3);

        List<Todo> todoList = todoRepository.findAll();

        assertThat(todoList).hasSize(3);
    }

    @Test
    @Transactional
    @Rollback(false)
    void testFindAllByMemberTest(){
        Member member1 = new Member("email1", "password1");
        Member member2 = new Member("email2", "password2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        Todo todo1 = new Todo ("todo content1",member1);
        Todo todo2 = new Todo ("todo content2",member1);
        Todo todo3 = new Todo ("todo content3",member2);
        todoRepository.save(todo1);
        todoRepository.save(todo2);
        todoRepository.save(todo3);

        List<Todo> member1TodoList = todoRepository.findAllByMember(member1);
        List<Todo> member2TodoList = todoRepository.findAllByMember(member2);

        assertThat(member1TodoList).hasSize(2);
        assertThat(member2TodoList).hasSize(1);
    }

    @Test
    @Transactional
    @Rollback(false)
    void todoUpdateTest(){
        Todo todo1 = new Todo ("todo content1",null);
        todoRepository.save(todo1);

        todoRepository.flushAndClear();

        Todo findTodo1 = todoRepository.findById(todo1.getId());
        findTodo1.updateContent("new Content");
    }

    @Test
    @Transactional
    @Rollback(false)
    void todoDeleteTest(){
        Todo todo1 = new Todo ("todo content1",null);
        Todo todo2 = new Todo ("todo content2",null);
        todoRepository.save(todo1);
        todoRepository.save(todo2);

        todoRepository.flushAndClear();

        todoRepository.deleteById(todo1.getId());
    }

    @AfterAll
    public static void doNotFinish(){
        System.out.println("test finished");
        while(true){

        }
    }
}
