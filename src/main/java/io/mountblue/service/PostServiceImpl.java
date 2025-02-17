package io.mountblue.service;

import io.mountblue.dto.PostDto;
import io.mountblue.models.Post;
import io.mountblue.models.Post_tag;
import io.mountblue.models.Tag;
import io.mountblue.models.User;
import io.mountblue.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final Post_TagRepository postTagRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository,
                           TagRepository tagRepository,
                           Post_TagRepository postTagRepository,
                           UserRepository userRepository){
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
        this.postTagRepository = postTagRepository;
        this.userRepository = userRepository;

    }
    @Override
    public void savePost(PostDto postDto, String tagList) {

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setExcerpt(createExcerpt(postDto.getContent()));
        post.setContent(postDto.getContent());
        if(postDto.getAuthor()!=null){
            post.setAuthor(postDto.getAuthor());
        }

        post.setIs_published(true);
        post.setPublished_at(LocalDateTime.now());
        post.setCreated_at(LocalDateTime.now());
        post.setUpdated_at(LocalDateTime.now());
        postRepository.save(post);

        List<String> tagLists = Arrays.asList(tagList.split(","));

        for(String tags:tagLists){
            Tag tag = tagRepository.findByName(tags);
            if(tag==null){
                tag=new Tag();
                tag.setName(tags.trim());
                tag.setCreated_at(LocalDateTime.now());
                tag.setUpdated_at(LocalDateTime.now());
                tagRepository.save(tag);
            }
            Post_tag post_tag = new Post_tag();
            post_tag.setPost(post);
            post_tag.setTag(tag);
            post_tag.setCreated_at(LocalDateTime.now());
            post_tag.setUpdated_at(LocalDateTime.now());
            postTagRepository.save(post_tag);
        }
    }

    private String createExcerpt(String content){
        String[] str= content.split("//.");
        StringBuilder excerpt = new StringBuilder();
        for(int i=0;i<1;i++){
            excerpt.append(str[i]);
        }
        return excerpt.toString();
    }
    @Override
    public List<Post> getAllPosts() {
        List<Post> listOfAllPost = postRepository.findAll();
        return listOfAllPost;
    }

    @Override
    public Post findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return  post;
    }

    @Override
    public void updatePost(PostDto postdto) {
        Post post = postRepository.findById(postdto.getId()).orElseThrow(() -> new RuntimeException("User not Found"));

        post.setTitle(postdto.getTitle());
        post.setContent(postdto.getContent());
        post.setUpdated_at((LocalDateTime.now()));
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        List<Post_tag> postTags = postTagRepository.findBypost_id(id);
        for(Post_tag tags:postTags){
            tagRepository.deleteById(tags.getTag().getId());
        }
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> searchPost(String keyword) {
        List<Post> listOfPost = postRepository.searchPosts(keyword);
        return listOfPost;
    }

}
