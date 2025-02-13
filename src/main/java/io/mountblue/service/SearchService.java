package io.mountblue.service;

import io.mountblue.models.Post;

import java.util.List;

public interface SearchService {
    List<Post> searchPost(String keyword);
}
