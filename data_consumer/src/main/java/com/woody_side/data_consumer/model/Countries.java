package com.woody_side.data_consumer.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "countries_data")
@Data
@Builder
public class Countries {

    @Id
    @Field(value = "country_id")
    private String id;

    @Field(value = "country_short_name")
    @Indexed(unique = true)
    private String shortName;

    @Field(value = "country_full_name")
    private String fullName;
}
