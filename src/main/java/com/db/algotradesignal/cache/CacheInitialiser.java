package com.db.algotradesignal.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class CacheInitialiser implements ApplicationListener<ApplicationReadyEvent> {

    private final List<Cache> caches;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        caches.forEach(Cache::initialiseCache);
    }

}