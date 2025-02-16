package io.mountblue.service;

import io.mountblue.models.Tag;

import java.util.List;

public interface TagService {
    void saveTag(String tagList);
    List<Tag> getAllTags();
}
