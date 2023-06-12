package com.api.julien.JavelotDemo.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TemperatureSend {

    @NonNull
    public Long temperatureId;
    @NonNull
    public Long cityId;

    public double temperature;

    public LocalDateTime date;

    public String name;

}
