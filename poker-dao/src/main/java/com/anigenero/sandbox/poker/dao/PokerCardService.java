package com.anigenero.sandbox.poker.dao;

import com.anigenero.sandbox.poker.dao.repository.StoryPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokerCardService {

    private final StoryPointRepository storyPointRepository;

    @Autowired
    public PokerCardService(StoryPointRepository storyPointRepository) {
        this.storyPointRepository = storyPointRepository;
    }

}
