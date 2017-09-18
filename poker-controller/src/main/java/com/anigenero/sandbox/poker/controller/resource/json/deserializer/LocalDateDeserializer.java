package com.anigenero.sandbox.poker.controller.resource.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {

        String value = parser.getValueAsString();
        if (value == null) {
            return null;
        } else if (StringUtils.isNumeric(value)) {
            return fromNumber(Long.valueOf(value));
        } else {
            return LocalDate.parse(value);
        }

    }

    /**
     * Gets the {@link LocalDate} from a long
     *
     * @param object {@link Long}
     * @return {@link LocalDate}
     */
    private LocalDate fromNumber(Long object) {
        return LocalDate.ofEpochDay(object);
    }

}
