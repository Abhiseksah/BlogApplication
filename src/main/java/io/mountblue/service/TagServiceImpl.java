package io.mountblue.service;

import io.mountblue.dto.TagDto;
import io.mountblue.models.Tag;
import io.mountblue.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TagServiceImpl implements  TagService{
    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    @Override
    public void saveTag(String tagList) {


    }

    @Override
    public List<Tag> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return tags;
    }
}
