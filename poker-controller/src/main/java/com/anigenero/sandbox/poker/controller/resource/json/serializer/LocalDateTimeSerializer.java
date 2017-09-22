package com.anigenero.sandbox.poker.controller.resource.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime value, JsonGenerator generator, SerializerProvider serializers) throws IOException {

        if (value != null) {

            Instant instant = value.atZone(ZoneId.systemDefault()).toInstant();
            Date date = Date.from(instant);

            generator.writeNumber(date.getTime());

        } else {
            generator.writeNull();
        }

    }

}
