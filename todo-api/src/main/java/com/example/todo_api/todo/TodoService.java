package com.example.todo_api.todo;

import com.example.todo_api.member.Member;
import com.example.todo_api.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;


    @Transactional // Todo 생성
    public Long createTodo(String content, Long memberId){
        Member member = memberRepository.findById(memberId);

        if(member == null){
            throw new RuntimeException("존재하지 않는 유저 ID 입니다.");
        }

        Todo todo = new Todo(content, member);

        todoRepository.save(todo);
        return todo.getId();
    }

    @Transactional(readOnly = true)
    public List<Todo> readTodos(Long memeberId){
        Member member = memberRepository.findById(memeberId);

        if(member == null){
            throw new RuntimeException("존재하지 않는 유저 ID 입니다.");
        }

        return todoRepository.findAllByMember(member);
    }

    @Transactional
    public void updateContent(Long memberId, Long todoId, String newContent){
        Todo todo = todoRepository.findById(todoId);
        Member member = memberRepository.findById(memberId);

        if(member == null) {
            throw new RuntimeException("멤버가 존재하지 않습니다.");
        }
        if(todo == null){
            throw new RuntimeException("할 일이 존재하지 않습니다.");
        }
        if(todo.getMember() != member){
            throw new RuntimeException("자신의 할 일만 수정할 수 있습니다.");
        }

        todo.updateContent(newContent);
    }

    @Transactional
    public void deleteContent(Long memberId, Long todoId){
        Todo todo = todoRepository.findById(todoId);
        Member member = memberRepository.findById(memberId);
        if(member == null) {
            throw new RuntimeException("멤버가 존재하지 않습니다.");
        }
        if(todo == null){
            throw new RuntimeException("할 일이 존재하지 않습니다.");
        }
        if(todo.getMember() != member){
            throw new RuntimeException("자신의 할 일만 삭제할 수 있습니다.");
        }

        todoRepository.deleteById(todoId);
    }
}
