package com.anigenero.sandbox.poker.dao.repository;

import com.anigenero.sandbox.poker.dao.jpa.entity.PokerCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokerCardRepository extends JpaRepository<PokerCardEntity, Short> {

    // only do selects

}
