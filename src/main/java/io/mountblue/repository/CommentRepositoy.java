package io.mountblue.repository;

import io.mountblue.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepositoy extends JpaRepository<Comment,Long> {
    List<Comment> findBypost_id(Long id);
}
