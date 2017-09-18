package com.anigenero.sandbox.poker.controller.resource.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {

        String value = parser.getValueAsString();
        if (value == null) {
            return null;
        } else if (StringUtils.isNumeric(value)) {
            return fromNumber(Long.valueOf(value));
        } else {
            return LocalDateTime.parse(value);
        }

    }

    /**
     * Gets the {@link LocalDateTime} from a long
     *
     * @param value {@link Long}
     * @return {@link LocalDateTime}
     */
    private LocalDateTime fromNumber(Long value) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneId.systemDefault());
    }

}
