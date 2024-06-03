package com.ThymeleafCrud.thymeleaf.crud.controller;

import com.ThymeleafCrud.thymeleaf.crud.Repository.TrendRepository;
import com.ThymeleafCrud.thymeleaf.crud.domain.Trend;
import com.ThymeleafCrud.thymeleaf.crud.service.TwitterScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class TrendController {

    @Autowired
    private TwitterScraperService twitterScraperService;

    @Autowired
    private TrendRepository trendRepository;

    @GetMapping("/run-script")
    public Trend runScript() {
        String[] trends = twitterScraperService.fetchTrendingTopics();
        String proxyIp = twitterScraperService.getProxy();

        Trend trend = new Trend();
        trend.setId(UUID.randomUUID().toString());
        trend.setTrend1(trends[0]);
        trend.setTrend2(trends[1]);
        trend.setTrend3(trends[2]);
        trend.setTrend4(trends[3]);
        trend.setTrend5(trends[4]);
        trend.setTimestamp(LocalDateTime.now());
        trend.setProxyIp(proxyIp);

        trendRepository.save(trend);
        return trend;
    }
}
