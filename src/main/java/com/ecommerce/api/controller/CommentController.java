package com.ecommerce.api.controller;

import com.ecommerce.api.domain.dto.CommentDto;
import com.ecommerce.api.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAll(){
        return ResponseEntity.ok(commentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable("id") Long id){
        return ResponseEntity.ok(commentService.get(id));
    }

    @PostMapping
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.add(commentDto));
    }

    //put

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id){
        commentService.delete(id);
    }

}
