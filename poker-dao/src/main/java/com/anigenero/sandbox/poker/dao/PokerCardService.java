package com.anigenero.sandbox.poker.dao;

import com.anigenero.sandbox.poker.dao.jpa.entity.PokerCardEntity;
import com.anigenero.sandbox.poker.dao.repository.PokerCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokerCardService {

    private final PokerCardRepository pokerCardRepository;

    @Autowired
    public PokerCardService(PokerCardRepository pokerCardRepository) {
        this.pokerCardRepository = pokerCardRepository;
    }

    /**
     * Gets all the poker cards in the repository
     *
     * @return {@link List} of {@link PokerCardEntity}
     */
    public List<PokerCardEntity> getPokerCards() {
        return this.pokerCardRepository.findAll();
    }

}
