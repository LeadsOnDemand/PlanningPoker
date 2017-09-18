package com.anigenero.sandbox.poker.dao.repository;

import com.anigenero.sandbox.poker.dao.jpa.entity.StoryPointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryPointRepository extends JpaRepository<StoryPointEntity, Short> {

    // only do selects

}
