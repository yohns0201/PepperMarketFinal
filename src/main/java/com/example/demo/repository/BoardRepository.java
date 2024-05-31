package com.example.demo.repository;

import com.example.demo.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    Page<Board> findByCateID(Integer searchCateID, Pageable pageable);
    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
    Page<Board> findByTitleContainingAndCateID(String searchKeyword, Integer searchCateID, Pageable pageable);
    void deleteById(Long id);



    @Query("SELECT b FROM Board b ORDER BY b.viewcount DESC")
    List<Board> findTop10ByOrderByViewcountDesc();

    List<Board> findByOrderByViewcountDesc();

    @Query("SELECT p.cateID, COUNT(p) FROM Board p GROUP BY p.cateID")
    List<Object[]> findCategoryPostCounts();



}