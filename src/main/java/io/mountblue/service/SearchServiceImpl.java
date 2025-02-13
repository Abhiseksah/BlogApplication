package io.mountblue.service;

import io.mountblue.models.Post;
import io.mountblue.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService{

    PostRepository postRepository;

    public SearchServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }
    @Override
    public List<Post> searchPost(String keyword) {
        List<Post> listOfPost = postRepository.searchPosts(keyword);
        return listOfPost;
    }
}
