package com.ecommerce.api.service;

import com.ecommerce.api.domain.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto get(Long id);
    CommentDto add(CommentDto commentDto);
    CommentDto update(CommentDto commentDto);
    void delete(Long id);
    List<CommentDto> getAll();
}
