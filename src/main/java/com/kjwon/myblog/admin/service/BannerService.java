package com.kjwon.myblog.admin.service;

import com.kjwon.myblog.admin.entity.Banner;
import com.kjwon.myblog.admin.model.BannerInput;

import java.util.List;

public interface BannerService {

    boolean addBanner(BannerInput bannerInput);

    List<Banner> bannerList();

    boolean delelteBanner(String idList);

    Banner updateBanner(Long userId);

    void updateBannerPost(BannerInput bannerInput);

    List<Banner> mainPage();
}
