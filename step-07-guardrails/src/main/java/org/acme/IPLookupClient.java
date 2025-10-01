package org.acme;

import dev.langchain4j.agent.tool.Tool;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://ip-api.com")
public interface IPLookupClient {

    @GET
    @Path("/json")
    @Tool("Get location based on public IP")
    String getLocation();
}
