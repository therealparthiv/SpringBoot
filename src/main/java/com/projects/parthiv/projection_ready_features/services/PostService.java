package com.projects.parthiv.projection_ready_features.services;

import com.projects.parthiv.projection_ready_features.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPosts();

    PostDto createNewPost(PostDto inputPost);

    PostDto getPostById(Long postId);
}
