package com.example.demo.repository;

import com.example.demo.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Spring Data JPA의 저장소로 사용
public interface BoardRepository extends JpaRepository<Board, Integer> {

    // 특정 카테고리 ID로 게시글을 페이징하여 검색
    Page<Board> findByCateID(Integer searchCateID, Pageable pageable);

    // 제목에 특정 키워드를 포함하는 게시글을 페이징하여 검색
    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);

    // 제목에 특정 키워드를 포함하고 특정 카테고리 ID를 가진 게시글을 페이징하여 검색
    Page<Board> findByTitleContainingAndCateID(String searchKeyword, Integer searchCateID, Pageable pageable);

    // ID를 사용하여 게시글 삭제
    void deleteById(Long id);

    // 조회수가 높은 상위 10개의 게시글을 조회하는 사용자 정의 쿼리
    @Query("SELECT b FROM Board b ORDER BY b.viewcount DESC")
    List<Board> findTop10ByOrderByViewcountDesc();

    // 조회수가 높은 순서로 모든 게시글을 조회
    List<Board> findByOrderByViewcountDesc();

    // 카테고리별 게시글 수를 조회하는 사용자 정의 쿼리
    @Query("SELECT p.cateID, COUNT(p) FROM Board p GROUP BY p.cateID")
    List<Object[]> findCategoryPostCounts();

    long countByUserId(Long userId);
}
