package com.adopt.cacheExample.service;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Getter
@Component
public class CacheManager {

    private static final Logger LOG = LoggerFactory.getLogger(CacheManager.class);

    @Autowired
    private ClientServiceCache clientServiceCache;

    public CacheManager(ClientServiceCache clientServiceCache) {
        this.clientServiceCache = clientServiceCache;
    }

    @PostConstruct
    public void load(){
        LOG.info("CacheManager loading from database...");
        List<ClientServiceCache> cacheList = Arrays.asList(clientServiceCache);
        cacheList.forEach(Cache::load);
    }

}
