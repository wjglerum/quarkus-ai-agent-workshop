package org.acme;

import io.quarkiverse.mcp.server.TextContent;
import io.quarkiverse.mcp.server.Tool;
import io.quarkiverse.mcp.server.ToolResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

public class WeatherMcpServer {

    @RestClient
    WeatherClientInternal weatherClient;

    @Tool(name = "current_weather", description = "Get current weather forecast for a location.")
    ToolResponse forecast(String latitude, String longitude) {
        String forecast = weatherClient.forecast(latitude, longitude);
        return ToolResponse.success(new TextContent(forecast));
    }
}
