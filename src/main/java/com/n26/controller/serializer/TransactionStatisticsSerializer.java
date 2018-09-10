package com.n26.controller.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.n26.entity.Statistics;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class TransactionStatisticsSerializer extends JsonSerializer<Statistics> {

    @Override
    public void serialize(Statistics statistics, JsonGenerator generator,
                          SerializerProvider provider) throws IOException {

        generator.writeStartObject();

        generator.writeStringField("sum", statistics.getSum().toString());
        generator.writeStringField("avg", statistics.getAvg().toString());
        generator.writeStringField("max", statistics.getMax().toString());
        generator.writeStringField("min", statistics.getMin().toString());
        generator.writeNumberField("count", statistics.getCount());

        generator.writeEndObject();
    }

}
