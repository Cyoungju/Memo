package com.sparta.demo.service;

import com.sparta.demo.dto.MemoRequestDto;
import com.sparta.demo.dto.MemoResponseDto;
import com.sparta.demo.entity.Memo;
import com.sparta.demo.repository.MemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemoService {

    private final MemoRepository memoRepository;


    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public MemoResponseDto createMemo(MemoRequestDto requestDto) {
        // RequestDto -> Entity
        Memo memo = new Memo(requestDto);

        // DB 저장
        Memo saveMemo = memoRepository.save(memo);

        // Entity -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(saveMemo);

        return memoResponseDto;
    }

    public List<MemoResponseDto> getMemos() {
        // DB 조회
        return memoRepository.findAllByOrderByModifiedAtDesc().stream().map(
                MemoResponseDto::new
        ).toList();
    }

    public List<MemoResponseDto> getMemosByKeyword(String Keyword){
        return memoRepository.findAllByContentsContainsOrderByModifiedAtDesc(Keyword).stream().map(
                MemoResponseDto::new
        ).toList();
    }

    @Transactional
    public Long updateMemo(Long id, MemoRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = findMemo(id);

        // memo 내용 수정
        memo.update(requestDto);

        return id;

    }

    public Long deleteMemo(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = findMemo(id);

        // memo 삭제
        memoRepository.delete(memo);

        return id;

    }


    private Memo findMemo(Long id){
        // 해당 메모가 DB에 존재하는지 확인
        return memoRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
}

