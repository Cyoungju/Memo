package com.sparta.demo.controller;

import com.sparta.demo.dto.MemoRequestDto;
import com.sparta.demo.dto.MemoResponseDto;
import com.sparta.demo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemoController {
    private final Map<Long,Memo> memoList = new HashMap<>();

    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto memoRequestDto){
        // requestDto -> Entity
        Memo memo = new Memo(memoRequestDto);

        // memo Max id check
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1;
        memo.setId(maxId);

        //DB저장
        memoList.put(memo.getId(), memo);

        // Entity -> responseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

        return memoResponseDto;
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos(){
        // Map To List
        List<MemoResponseDto> memoResponseDtos =  memoList.values().stream()
                .map(MemoResponseDto::new).toList();
                //MemoResponseDto 생성자 호출

        return memoResponseDtos;
    }

    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
        //해당 메모가 데이터베이스에 존재 하는지 확인
        if(memoList.containsKey(id)){
            Memo memo = memoList.get(id);
            //메모 수정
            memo.update(requestDto);
            return memo.getId();
        }else {
            throw new IllegalArgumentException("해당 메모는 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id){
        //데이터베이스에 존재하는지 확인
        if(memoList.containsKey(id)){
            memoList.remove(id);
            return id;
        }else {
            throw new IllegalArgumentException("해당 메모는 존재하지 않습니다.");
        }
    }

}
