package com.api.julien.JavelotDemo.model;

import lombok.*;
import lombok.experimental.SuperBuilder;


@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class City {

    public String name;

    public double longitude;

    public double latitude;

    public Long cityId;



}
