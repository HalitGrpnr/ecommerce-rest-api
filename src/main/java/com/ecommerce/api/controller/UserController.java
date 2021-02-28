package com.ecommerce.api.controller;

import com.ecommerce.api.domain.dto.UserDto;
import com.ecommerce.api.domain.request.UserAddRequest;
import com.ecommerce.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> get(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.get(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> add(@RequestBody UserAddRequest request){
        return ResponseEntity.ok(userService.add(request));
    }

    //put

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }

}
