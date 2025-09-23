package org.acme;

import io.quarkus.security.Authenticated;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestQuery;

@Authenticated
@Path("/weather")
public class WeatherResource {

    @RestClient
    WeatherClient weatherClient;

    @GET
    @Path("/forecast")
    public String forecast(@RestQuery String latitude,
                           @RestQuery String longitude) {
        return weatherClient.forecast(latitude, longitude, "temperature_2m,wind_speed_10m,precipitation");
    }
}
