package com.ecommerce.api.controller;

import com.ecommerce.api.domain.dto.HomePageDto;
import com.ecommerce.api.service.HomePageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/api/home")
public class HomePageController {

    private final HomePageService homePageService;

    public HomePageController(HomePageService homePageService) {
        this.homePageService = homePageService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<HomePageDto> getHomePageData(@PathVariable("userId") Long userId){
        try {
            HomePageDto homePageData = homePageService.getHomePageData(userId);
            return ResponseEntity.ok(homePageData);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<HomePageDto> getHomePageData(){
        try {
            HomePageDto homePageData = homePageService.getHomePageData();
            return ResponseEntity.ok(homePageData);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
