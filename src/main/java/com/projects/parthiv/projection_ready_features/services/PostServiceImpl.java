package com.projects.parthiv.projection_ready_features.services;

import com.projects.parthiv.projection_ready_features.dto.PostDto;
import com.projects.parthiv.projection_ready_features.entities.PostEntity;
import com.projects.parthiv.projection_ready_features.exceptions.ResourceNotFoundException;
import com.projects.parthiv.projection_ready_features.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements  PostService {

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;
    @Override
    public List<PostDto> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createNewPost(PostDto inputPost) {
        PostEntity postEntity= modelMapper.map(inputPost, PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity), PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        PostEntity postEntity= postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: "+ postId));
        return modelMapper.map(postEntity, PostDto.class);
    }

}
