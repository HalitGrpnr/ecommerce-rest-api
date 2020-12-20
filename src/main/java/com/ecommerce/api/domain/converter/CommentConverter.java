package com.ecommerce.api.domain.converter;

import com.ecommerce.api.domain.dto.CommentDto;
import com.ecommerce.api.domain.entity.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentConverter implements BaseConverter<CommentDto, Comment> {
    @Override
    public CommentDto convertToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();

        commentDto.setContent(comment.getContent());
        commentDto.setId(comment.getId());
        commentDto.setCreatedDate(comment.getCreatedDate());

        return commentDto;
    }

    @Override
    public Comment convertToEntity(CommentDto commentDto) {
        Comment comment = new Comment();

        comment.setContent(commentDto.getContent());
        comment.setCreatedDate(commentDto.getCreatedDate());

        return comment;
    }
}
