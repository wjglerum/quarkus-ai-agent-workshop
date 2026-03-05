package org.acme;

import io.quarkiverse.mcp.server.TextContent;
import io.quarkiverse.mcp.server.Tool;
import io.quarkiverse.mcp.server.ToolResponse;

public class JavaLandMcpServer {

    @Tool(name = "conference_schedule", description = "Get JavaLand 2026 conference talks happening at a specific time. Provide the day (Tuesday or Wednesday) and time in HH:mm format (e.g. 09:00, 14:00).")
    ToolResponse whatsOnNow(String day, String time) {
        var talks = JavaLandSchedule.findHappeningAt(day, time);
        return ToolResponse.success(new TextContent(JavaLandSchedule.formatTalks(talks)));
    }

    @Tool(name = "find_speaker", description = "Find talks by a specific speaker at JavaLand 2026. Provide the speaker's name (or part of it).")
    ToolResponse findSpeaker(String speaker) {
        var talks = JavaLandSchedule.findBySpeaker(speaker);
        return ToolResponse.success(new TextContent(JavaLandSchedule.formatTalks(talks)));
    }

    @Tool(name = "find_talk_by_topic", description = "Search for JavaLand 2026 talks by topic keyword (e.g. 'AI', 'security', 'testing', 'Java', 'Spring').")
    ToolResponse findByTopic(String topic) {
        var talks = JavaLandSchedule.findByTopic(topic);
        return ToolResponse.success(new TextContent(JavaLandSchedule.formatTalks(talks)));
    }

    @Tool(name = "next_talks", description = "Get the next upcoming talks at JavaLand 2026 after a given time. Provide the day and current time in HH:mm format.")
    ToolResponse nextTalks(String day, String currentTime) {
        var talks = JavaLandSchedule.getNextTalks(day, currentTime);
        return ToolResponse.success(new TextContent(JavaLandSchedule.formatTalks(talks)));
    }
}
