package io.mountblue.repository;

import io.mountblue.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long > {
}
