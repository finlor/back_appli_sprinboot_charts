    package com.api.julien.JavelotDemo.dataAccess;

    import com.api.julien.JavelotDemo.model.City;
    import com.api.julien.JavelotDemo.model.CityTemperature;
    import com.api.julien.JavelotDemo.model.CityTemperatureDb;
    import com.api.julien.JavelotDemo.model.Temperatures;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.jdbc.core.JdbcTemplate;
    import org.springframework.jdbc.core.RowMapper;
    import org.springframework.stereotype.Repository;

    import java.time.LocalDateTime;
    import java.time.format.DateTimeFormatter;
    import java.util.ArrayList;
    import java.util.List;

    @Repository
    public class TemperatureDataAccessService {

        public final JdbcTemplate jdbcTemplate;

        @Autowired
        public TemperatureDataAccessService(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        public CityTemperature selectAllTemperatureByCity(Long cityId) {
            String sql = "" +
                    "SELECT " +
                    " temperature.temperature_id, " +
                    " temperature.city_id, " +
                    " temperature.temperature, " +
                    " temperature.date, " +
                    " city.name, " +
                    " city.longitude, " +
                    " city.latitude " +
                    "FROM temperature " +
                    "JOIN city USING (city_id) " +
                    "WHERE temperature.city_id = ?";


            return transformerCityTemperatureToAPI(jdbcTemplate.query(
                    sql,
                    new Object[]{cityId},
                    mapTemperatureCityFromDb()
            ));
        }


        public List<City> selectAllCity() {
            String sql = "" +
                    "SELECT " +
                    " city.city_id, " +
                    " city.name, " +
                    " city.latitude, " +
                    " city.longitude " +
                    "FROM city ";
            return jdbcTemplate.query(sql, mapCityFomDb());
        }

        public RowMapper<CityTemperatureDb> mapTemperatureCityFromDb() {
            return (resultSet, i) ->
                    new CityTemperatureDb(
                            resultSet.getLong("temperature_id"),
                            resultSet.getLong("city_id"),
                            resultSet.getDouble("temperature"),
                            LocalDateTime.parse(resultSet.getString("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                            resultSet.getString("name"),
                            resultSet.getDouble("longitude"),
                            resultSet.getDouble("latitude")
                    );
        }

        public   RowMapper<City> mapCityFomDb() {
            return (resultSet, i) ->
                    new City(
                            resultSet.getString("name"),
                            resultSet.getDouble("longitude"),
                            resultSet.getDouble("latitude"),
                            resultSet.getLong("city_id")
                    );
        }

        public CityTemperature transformerCityTemperatureToAPI(List<CityTemperatureDb> cityTemperatureDb) {
            CityTemperatureDb cityData = cityTemperatureDb
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No temperature found for this city: "));

            City cityTemperature = new City(
                    cityData.name,
                    cityData.longitude,
                    cityData.latitude,
                    cityData.cityId
            );

            List<Temperatures> temperaturesList = new ArrayList<Temperatures>();
            for (CityTemperatureDb temperature: cityTemperatureDb){
               Temperatures temperatureObject = new Temperatures(temperature.getTemperature(), temperature.getDate());
                temperaturesList.add(temperatureObject);
            }

            return new CityTemperature(
                    cityTemperature,
                    temperaturesList );
        }
    }
