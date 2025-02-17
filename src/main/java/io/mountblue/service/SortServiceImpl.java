package io.mountblue.service;

import io.mountblue.models.Post;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SortServiceImpl implements SortService{
    @Override
    public List<Post> sortedPost(List<Post> posts,String sortingStrategy) {

        if(sortingStrategy.equals("asc")){
            Collections.sort(posts, new Comparator<Post>() {
                @Override
                public int compare(Post post1, Post post2) {
                    return post2.getCreated_at().compareTo(post1.getCreated_at());
                }
            });
        }else{
            Collections.sort(posts, new Comparator<Post>() {
                @Override
                public int compare(Post post1, Post post2) {
                    return post1.getCreated_at().compareTo(post2.getCreated_at());
                }
            });
        }

        return posts;
    }
}
