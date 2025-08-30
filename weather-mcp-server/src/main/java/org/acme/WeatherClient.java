package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestQuery;

@Path("/v1")
@RegisterRestClient(baseUri = "https://api.open-meteo.com")
public interface WeatherClient {

    @GET
    @Path("/forecast")
    String forecast(@RestQuery String latitude,
                    @RestQuery String longitude,
                    @RestQuery String current
    );
}
