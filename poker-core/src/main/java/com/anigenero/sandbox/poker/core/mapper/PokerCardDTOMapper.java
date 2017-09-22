package com.anigenero.sandbox.poker.core.mapper;

import com.anigenero.sandbox.poker.controller.model.PokerCardDTO;
import com.anigenero.sandbox.poker.dao.jpa.entity.PokerCardEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PokerCardDTOMapper {

    PokerCardDTO toPokerCardDTO(PokerCardEntity pokerCardEntity);

    @IterableMapping(elementTargetType = PokerCardDTO.class)
    List<PokerCardDTO> toPokerCardDTOList(List<PokerCardEntity> pokerCards);

}
