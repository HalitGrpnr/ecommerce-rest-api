package com.ecommerce.api.service.implementation;

import com.ecommerce.api.domain.converter.CommentConverter;
import com.ecommerce.api.domain.dto.CommentDto;
import com.ecommerce.api.domain.entity.Comment;
import com.ecommerce.api.repository.CommentRepository;
import com.ecommerce.api.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    
    private CommentRepository commentRepository;
    private CommentConverter commentConverter;

    public CommentServiceImpl(CommentRepository commentRepository, CommentConverter commentConverter) {
        this.commentRepository = commentRepository;
        this.commentConverter = commentConverter;
    }

    @Override
    public CommentDto get(Long id) {
        Comment comment = commentRepository.findById(id).orElse(new Comment());
        CommentDto commentDto = commentConverter.convertToDto(comment);
        return commentDto;
    }

    @Override
    public CommentDto add(CommentDto commentDto) {
        Comment comment = commentConverter.convertToEntity(commentDto);
        comment = commentRepository.save(comment);

        return commentConverter.convertToDto(comment);
    }

    @Override
    public CommentDto update(CommentDto commentDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<CommentDto> getAll() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> commentDtos = comments.stream().map(c -> commentConverter.convertToDto(c)).collect(Collectors.toList());
        return commentDtos;
    }
}
