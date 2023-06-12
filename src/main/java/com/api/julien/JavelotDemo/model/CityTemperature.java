package com.api.julien.JavelotDemo.model;

        import lombok.*;
        import lombok.experimental.SuperBuilder;
        import java.util.List;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CityTemperature {

    public City city_info;
    public List<Temperatures> data;

}
