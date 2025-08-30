package org.acme;

import io.quarkiverse.mcp.server.TextContent;
import io.quarkiverse.mcp.server.Tool;
import io.quarkiverse.mcp.server.ToolResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

public class WeatherMcpServer {

    @RestClient
    WeatherClient weatherClient;

    @Tool(name = "Current weather", description = "Get current weather forecast for a location.")
    ToolResponse forecast(String latitude, String longitude) {
        String forecast = weatherClient.forecast(latitude, longitude, "temperature_2m,wind_speed_10m,precipitation");
        return ToolResponse.success(new TextContent(forecast));
    }
}
