package com.anigenero.sandbox.poker.dao.jpa.listener;

import com.anigenero.sandbox.poker.dao.jpa.entity.PokerCardEntity;
import com.anigenero.sandbox.poker.dao.repository.PokerCardRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class JpaContextListener implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LogManager.getLogger(JpaContextListener.class);

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        try {

            PokerCardRepository pokerCardRepository = applicationContext.getBean(PokerCardRepository.class);

            log.info("Populating poker cards...");

            pokerCardRepository.save(getQuestions());
            pokerCardRepository.flush();

        } catch (IOException e) {
            log.error("Could not insert poker cards into the database", e);
        }

    }

    /**
     * Gets the questions from the included resource file
     *
     * @return {@link List} of {@link PokerCardEntity}
     * @throws IOException if there is an error reading the file
     */
    private List<PokerCardEntity> getQuestions() throws IOException {

        InputStream inputStream = JpaContextListener.class.getResourceAsStream("/poker_cards.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        List<PokerCardEntity> cardSet = new ArrayList<>();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            cardSet.add(toPokerCardEntity(line));
        }

        return cardSet;

    }

    /**
     * Converts the line to a {@link PokerCardEntity}. The format is "{value} | {symbol}"
     *
     * @param line {@link String}
     * @return {@link PokerCardEntity}
     */
    private PokerCardEntity toPokerCardEntity(String line) {

        String[] parts = line.split("\\|");

        PokerCardEntity entity = new PokerCardEntity();
        entity.setValue(Double.parseDouble(parts[0].trim()));

        if (parts.length == 2) {
            entity.setSymbol(parts[1].trim());
        }

        return entity;

    }

}