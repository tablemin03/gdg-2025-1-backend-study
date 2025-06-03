package com.example.todo_api.todo;

import com.example.todo_api.member.Member;
import com.example.todo_api.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.todo_api.common.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;


    @Transactional // Todo 생성
    public Long createTodo(String content, Long memberId){
        Member member = memberRepository.findById(memberId);

        if(member == null){
            throw new RuntimeException(MEMBER_NOT_EXIST);
        }

        Todo todo = new Todo(content, member);

        todoRepository.save(todo);
        return todo.getId();
    }

    @Transactional(readOnly = true)
    public List<Todo> readTodos(Long memeberId){
        Member member = memberRepository.findById(memeberId);

        if(member == null){
            throw new RuntimeException(MEMBER_NOT_EXIST);
        }

        return todoRepository.findAllByMember(member);
    }

    @Transactional
    public void updateContent(Long memberId, Long todoId, String newContent){
        Todo todo = todoRepository.findById(todoId);
        Member member = memberRepository.findById(memberId);

        if(member == null) {
            throw new RuntimeException(MEMBER_NOT_EXIST);
        }
        if(todo == null){
            throw new RuntimeException(TODO_NOT_EXIST);
        }
        if(todo.getMember() != member){
            throw new RuntimeException(NOT_YOUR_TODO);
        }

        todo.updateContent(newContent);
    }

    @Transactional
    public void deleteContent(Long memberId, Long todoId){
        Todo todo = todoRepository.findById(todoId);
        Member member = memberRepository.findById(memberId);
        if(member == null) {
            throw new RuntimeException(MEMBER_NOT_EXIST);
        }
        if(todo == null){
            throw new RuntimeException(TODO_NOT_EXIST);
        }
        if(todo.getMember() != member){
            throw new RuntimeException(NOT_YOUR_TODO);
        }

        todoRepository.deleteById(todoId);
    }
}
