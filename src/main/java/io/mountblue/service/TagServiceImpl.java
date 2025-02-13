package io.mountblue.service;

import io.mountblue.dto.TagDto;
import io.mountblue.models.Tag;
import io.mountblue.repository.TagRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagServiceImpl implements  TagService{
    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    @Override
    public void saveTag(String tagList) {


    }
}
