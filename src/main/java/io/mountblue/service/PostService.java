package io.mountblue.service;

import io.mountblue.dto.PostDto;
import io.mountblue.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.List;

public interface PostService {
    void savePost(PostDto postDto,String tagList);
    List<Post> getAllPosts();
    Post findPostById(Long id);
    void updatePost(PostDto postdto);
    void deletePost(Long id);
    Page<Post> findFilteredPosts(Long authorId, List<Long> tagIds, Boolean isPublished,
                                 LocalDateTime startDate, LocalDateTime endDate, Pageable pageable , String sortBy, String sortOrder) ;
}
