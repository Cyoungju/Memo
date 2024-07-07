package com.sparta.demo.repository;

import com.sparta.demo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findAllByOrderByModifiedAtDesc();
    //ModifiedAt 정렬해서 내림차순으로 불러오기

    List<Memo> findAllByContentsContainsOrderByModifiedAtDesc(String keyword);

}