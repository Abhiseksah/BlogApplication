package io.mountblue.service;

import io.mountblue.models.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SortService {
    List<Post> sortedPost(List<Post> posts,String sortingStrategy);
}
