package com.anigenero.sandbox.poker.controller.resource.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.LocalTime;

public class LocalTimeDeserializer extends JsonDeserializer<LocalTime> {

    @Override
    public LocalTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {

        String value = parser.getValueAsString();
        if (StringUtils.isNumeric(value)) {
            return fromNumber(Long.valueOf(value));
        } else {
            return LocalTime.parse(value);
        }

    }

    /**
     * Gets the {@link LocalTime} from a long
     *
     * @param object {@link Long}
     * @return {@link LocalTime}
     */
    private LocalTime fromNumber(Long object) {
        return LocalTime.ofSecondOfDay(object);
    }

}
