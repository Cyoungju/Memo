package com.sparta.demo.repository;

import com.sparta.demo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


public interface MemoRepository extends JpaRepository<Memo, Long> {

}