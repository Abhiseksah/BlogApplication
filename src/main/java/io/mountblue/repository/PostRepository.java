package io.mountblue.repository;

import io.mountblue.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long >, JpaSpecificationExecutor<Post> {

    @Query("SELECT DISTINCT p " +
            "FROM Post p " +
            "LEFT JOIN p.postTags pt " +
            "LEFT JOIN pt.tag t " +
            "WHERE LOWER(CONCAT(' ', p.author, ' ')) " +
            "LIKE LOWER(CONCAT('% ', :keyword, ' %')) OR " +
            "LOWER(CONCAT(' ', p.title, ' ')) " +
            "LIKE LOWER(CONCAT('% ', :keyword, ' %')) OR " +
            "LOWER(CONCAT(' ', p.content, ' ')) " +
            "LIKE LOWER(CONCAT('% ', :keyword, ' %')) OR " +
            "LOWER(CONCAT(' ', t.name, ' ')) " +
            "LIKE LOWER(CONCAT('% ', :keyword, ' %'))")
    List<Post> searchPosts(@Param("keyword") String keyword);



}
