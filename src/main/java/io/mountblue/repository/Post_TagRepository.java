package io.mountblue.repository;

import io.mountblue.models.Post_tag;
import io.mountblue.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Post_TagRepository extends JpaRepository<Post_tag,Long> {
    List<Post_tag> findBypost_id(Long id);

@Query("SELECT pt.tag.name " +
        "FROM Post_tag pt " +
        "WHERE pt.post.id = :postId")
List<String> findTagsByPostId(@Param("postId") Long postId);

}
