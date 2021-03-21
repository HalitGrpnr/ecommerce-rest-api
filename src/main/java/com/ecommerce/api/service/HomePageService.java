package com.ecommerce.api.service;

import com.ecommerce.api.domain.dto.HomePageDto;

public interface HomePageService {
    HomePageDto getHomePageData(Long userId);
    HomePageDto getHomePageData();
}
