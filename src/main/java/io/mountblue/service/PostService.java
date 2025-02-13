package io.mountblue.service;

import io.mountblue.dto.PostDto;
import io.mountblue.models.Post;

import java.util.List;

public interface PostService {
    void savePost(PostDto postDto,String tagList);
    List<Post> getAllPosts();
    Post findPostById(Long id);
    void updatePost(PostDto postdto);
    void deletePost(Long id);
}
