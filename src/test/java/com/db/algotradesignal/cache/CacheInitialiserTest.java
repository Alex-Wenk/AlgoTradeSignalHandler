package com.db.algotradesignal.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CacheInitialiserTest {

    @InjectMocks
    CacheInitialiser cacheInitialiser;
    @Mock
    Cache cache;
    @Spy
    List<Cache> listOfCaches = new ArrayList<>();

    @BeforeEach
    void setup(){
        listOfCaches.add(cache);
    }

    @Test
    void onApplicationEvent() {
        cacheInitialiser.onApplicationEvent(mock(ApplicationReadyEvent.class));
        verify(cache).initialiseCache();
    }
}