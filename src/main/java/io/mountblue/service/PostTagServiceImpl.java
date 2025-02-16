package io.mountblue.service;

import io.mountblue.models.Tag;
import io.mountblue.repository.Post_TagRepository;
import io.mountblue.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostTagServiceImpl implements PostTagService{
    private final Post_TagRepository postTagRepository;
    private final TagRepository tagRepository;

    public PostTagServiceImpl(Post_TagRepository postTagRepository,TagRepository tagRepository){
        this.postTagRepository = postTagRepository;
        this.tagRepository = tagRepository;
    }
    @Override
    public String FindTagbyPost(Long id) {
        List<String> tag = postTagRepository.findTagsByPostId(id);
        StringBuilder tagString =new StringBuilder();
        for(String tags:tag){
            tagString.append(tags);
            tagString.append(",");
        }
        if(tagString.length()>0){
            tagString.deleteCharAt(tagString.length()-1);
        }
        return tagString.toString();
    }
}
