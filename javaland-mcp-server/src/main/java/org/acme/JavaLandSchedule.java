package org.acme;

import java.util.List;
import java.util.stream.Collectors;

public class JavaLandSchedule {

    public record Talk(String day, String time, String endTime, String title, String speaker, String room, String category) {}

    private static final List<Talk> TALKS = List.of(
        // Tuesday March 10 - 08:30
        new Talk("Tuesday", "08:30", "09:10", "Coding mise en place - DDD und Ports-and-Adapters", "Dominik Galler", "Bellevue", "Architecture"),
        new Talk("Tuesday", "08:30", "09:10", "Generics - You Never Know What You're Gonna Get", "Bram Janssens", "Sanssouci", "Java"),
        new Talk("Tuesday", "08:30", "09:10", "A billion dollar problem: Proper null handling in Java", "Benedikt Loup", "Versailles", "Java"),
        new Talk("Tuesday", "08:30", "09:10", "Secure AI agents with Quarkus LangChain4j", "Willem Jan Glerum", "CircusCelebration", "AI"),
        new Talk("Tuesday", "08:30", "09:10", "The road to JUnit 6", "Marc Philipp", "Das Studio", "Testing"),
        new Talk("Tuesday", "08:30", "09:10", "Logs, Metriken, Traces - Monitoring fuer Java im Kubernetes-Umfeld", "Thorsten Wussow", "Jeanne d'Arc", "DevOps"),

        // Tuesday 10:00 - Keynote
        new Talk("Tuesday", "10:00", "10:50", "KEYNOTE: AI: It AI-n't What You Think!", "Dr. Venkat Subramaniam", "Dome", "Keynote"),

        // Tuesday 11:00
        new Talk("Tuesday", "11:00", "11:40", "KI + AST + ESLint = Codequalitaet auf dem naechsten Level", "Milena Fluck, Andy Schmidt", "Bellevue", "AI"),
        new Talk("Tuesday", "11:00", "11:40", "Scaling Integration Tests: Parallel Spring Tests with JUnit and Testcontainers", "Marvin Rensing", "Versailles", "Testing"),
        new Talk("Tuesday", "11:00", "11:40", "Apps fuer alle: Accessibility als Mobile Entwickler", "Giulia Maier", "Dome", "Mobile"),
        new Talk("Tuesday", "11:00", "11:40", "Korrektheit trotz Nicht-Determinismus: KI-Anforderungen in der Praxis", "Dr. Felix Kammerlander", "Magic Cinema", "AI"),

        // Tuesday 12:00
        new Talk("Tuesday", "12:00", "12:40", "BoxLang: The Return of the Dynamic Language", "Luis Majano", "Bellevue", "Languages"),
        new Talk("Tuesday", "12:00", "12:40", "Clean Architecture: Fundament wartbarer Software oder nur ein Hype?", "Fedor Zholud", "Versailles", "Architecture"),
        new Talk("Tuesday", "12:00", "12:40", "AI Testing Beyond the Basics: Ensuring Truthful and Reliable Chatbots and Agents", "Mario-Leander Reimer", "Dome", "AI"),
        new Talk("Tuesday", "12:00", "12:40", "The Trust Trap - Security von Coding Assistants", "Clemens Huebner", "Magic Cinema", "Security"),

        // Tuesday 13:00
        new Talk("Tuesday", "13:00", "13:40", "Modern Java - Ask Me Anything", "Nicolai Parlog", "Sanssouci", "Java"),
        new Talk("Tuesday", "13:00", "13:40", "Deep dive into data streaming security", "Olena Kutsenko", "Salle Petit Paris", "Security"),
        new Talk("Tuesday", "13:00", "13:40", "The Past, the Present, and the Future of Enterprise Java", "Ivar Grimstad", "Dome", "Java"),
        new Talk("Tuesday", "13:00", "13:40", "Der Keycloak-Fehler, den 90% aller Entwickler machen", "Niko Koebler", "CircusCelebration", "Security"),

        // Tuesday 14:00
        new Talk("Tuesday", "14:00", "14:40", "Bessere Assertions und Fehlermeldungen mit AssertJ", "Ole Koring", "Sanssouci", "Testing"),
        new Talk("Tuesday", "14:00", "14:40", "The Road Not Taken: A Developer's Guide to Life Beyond Spring Boot", "Frederik Pietzko", "Das Studio", "Frameworks"),
        new Talk("Tuesday", "14:00", "14:40", "Exploring Structured Concurrency and Scoped Values in Java 25", "Hanno Embregts", "Magic Cinema", "Java"),

        // Tuesday 15:00
        new Talk("Tuesday", "15:00", "15:40", "EU AI Act: Compliance ohne Kopfschmerzen", "Christian Fritz, Alexander Eimer", "CircusCelebration", "AI"),
        new Talk("Tuesday", "15:00", "15:40", "Was gibts Neues bei Spring Boot und Containern?", "Matthias Haeussler", "Das Studio", "Frameworks"),
        new Talk("Tuesday", "15:00", "15:45", "Post-Quantum-Cryptography (PQC) in Java", "Tim Schade", "Magic Cinema", "Security"),

        // Tuesday 16:00
        new Talk("Tuesday", "16:00", "16:40", "Spring AI trifft MCP: Live-Entwicklung einer smarten Anwendung", "Bernhard Loewenstein, Ionida Loewenstein", "Bellevue", "AI"),
        new Talk("Tuesday", "16:00", "16:40", "Von Legacy zu Cloud: KI-gestuetzte Java-Modernisierung mit Konveyor", "Markus Zimmermann", "Das Studio", "AI"),
        new Talk("Tuesday", "16:00", "16:40", "Getting more out of Maven", "Marit van Dijk, Andres Almiray", "Dome", "Tooling"),
        new Talk("Tuesday", "16:00", "16:40", "82 Bugs I Collected in a Year You Won't Believe Made It to Production", "Francois Martin", "Magic Cinema", "Quality"),

        // Tuesday 17:00
        new Talk("Tuesday", "17:00", "17:40", "AI-Powered Form Wizards: Chat, Click, Done", "Loic Magnette", "Das Studio", "AI"),
        new Talk("Tuesday", "17:00", "17:40", "Service-to-service authentication mit Keycloak und Spring Security", "Nikolas Hermann", "Magic Cinema", "Security"),
        new Talk("Tuesday", "17:00", "17:40", "Snake in 10 Lines: Learning More by Coding Less", "Guus de Wit", "Salle Petit Paris", "Fun"),

        // Wednesday March 11 - 09:00
        new Talk("Wednesday", "09:00", "09:40", "Jakarta Data - Das Ende des Boilerplate Code?", "Thorben Janssen", "Bellevue", "Java"),
        new Talk("Wednesday", "09:00", "09:40", "Softwarearchaeologie mit KI", "Markus Harrer", "CircusCelebration", "AI"),
        new Talk("Wednesday", "09:00", "09:40", "AI-driven reverse engineering", "Martin Toshev", "Das Studio", "AI"),
        new Talk("Wednesday", "09:00", "09:40", "Observing Project Valhalla", "Prof. Dr. Cay Horstmann", "Dome", "Java"),
        new Talk("Wednesday", "09:00", "09:40", "No More Works on My Machine - Reproducible Dev Environments Made Easy", "Sascha Selzer", "Magic Cinema", "DevOps"),

        // Wednesday 10:00
        new Talk("Wednesday", "10:00", "10:40", "Panel: Java (25)", "Panel", "Bellevue", "Java"),
        new Talk("Wednesday", "10:00", "10:40", "DevOps Meets MIDI: Automate Everything with the Push of a Button!", "Mario-Leander Reimer", "Salle Petit Paris", "Fun"),
        new Talk("Wednesday", "10:00", "10:40", "Vector Search Made Simple: Getting Started with OpenSearch for AI Applications", "Dotan Horovits", "Das Studio", "AI"),
        new Talk("Wednesday", "10:00", "10:40", "Von chaotischem AI-Code zu strukturierten Workflows: Rule-Files in der Praxis", "Ferdinand Ade, Marco Emrich", "Jeanne d'Arc", "AI"),

        // Wednesday 11:00
        new Talk("Wednesday", "11:00", "11:40", "Community Keynote: Live-Awards, Voting and Community-Momente", "Community", "Dome", "Keynote"),

        // Wednesday 12:00
        new Talk("Wednesday", "12:00", "12:40", "Bootiful Spring Boot: A DOGumentary", "Josh Long", "Dome", "Frameworks"),
        new Talk("Wednesday", "12:00", "12:40", "Catching the 137-Killer: A Java Memory Forensics Investigation", "Martijn Dashorst", "Jeanne d'Arc", "Java"),
        new Talk("Wednesday", "12:00", "12:40", "Building a real-time collaborative editor", "Dr. Mihaela Gheorghe-Roman", "Das Studio", "Architecture"),

        // Wednesday 12:30
        new Talk("Wednesday", "12:30", "15:30", "Agentic Coding mit Java - Professionelle Softwareentwicklung mit KI-Assistenz", "Frederik Wystup", "Traumpalast", "AI"),

        // Wednesday 13:00
        new Talk("Wednesday", "13:00", "13:40", "Local LLMs to Enterprise AI: from your laptop to production", "Marta Blaszczyk, Marco Klaassen", "Dome", "AI"),
        new Talk("Wednesday", "13:00", "13:40", "Mein DDD ist kaputt - Wenn Architektur allein nicht reicht", "Andreas Voigt", "Bellevue", "Architecture"),

        // Wednesday 14:00
        new Talk("Wednesday", "14:00", "14:40", "The Evolution of Design Patterns in the Age of AI", "Miroslav Wengner", "Dome", "AI"),
        new Talk("Wednesday", "14:00", "14:40", "Vektordatenbanken - Grundlagen und praktische Anwendungen", "Prof. Dr. Olaf Herden", "CircusCelebration", "AI"),

        // Wednesday 15:00
        new Talk("Wednesday", "15:00", "15:40", "Von REST zu Agentic-API: Den LLM-API-Consumer verstehen", "Felix Medam", "Sanssouci", "AI"),
        new Talk("Wednesday", "15:00", "15:40", "Die All Stars der Software Bugs - Staffel 2", "Christian Seifert", "Dome", "Quality"),

        // Wednesday 16:00
        new Talk("Wednesday", "16:00", "16:40", "From CRUD to Cognitive: Modernizing Legacy Java Apps with Embedded AI", "Ed Burns", "Dome", "AI"),
        new Talk("Wednesday", "16:00", "16:40", "Nieder mit den Mocks - Quarkus TestResources", "Benedikt Werner", "Salle Petit Paris", "Testing"),
        new Talk("Wednesday", "16:00", "16:40", "Kontext ist alles: Architekturdokumentation fuer Mensch und KI", "Marcus Held", "Magic Cinema", "AI"),

        // Wednesday 17:00
        new Talk("Wednesday", "17:00", "17:40", "Legacy-Modernisierung mit DDD und Event Storming", "Sven Mueller", "Dome", "Architecture"),
        new Talk("Wednesday", "17:00", "17:40", "Old and busted: git. New hotness: jujutsu.", "Christoph Iserlohn", "Jeanne d'Arc", "Tooling")
    );

    public static List<Talk> findByTime(String day, String time) {
        return TALKS.stream()
                .filter(t -> t.day().equalsIgnoreCase(day))
                .filter(t -> t.time().equals(time))
                .collect(Collectors.toList());
    }

    public static List<Talk> findHappeningAt(String day, String time) {
        return TALKS.stream()
                .filter(t -> t.day().equalsIgnoreCase(day))
                .filter(t -> t.time().compareTo(time) <= 0 && t.endTime().compareTo(time) > 0)
                .collect(Collectors.toList());
    }

    public static List<Talk> findBySpeaker(String speakerQuery) {
        String q = speakerQuery.toLowerCase();
        return TALKS.stream()
                .filter(t -> t.speaker().toLowerCase().contains(q))
                .collect(Collectors.toList());
    }

    public static List<Talk> findByTopic(String topicQuery) {
        String q = topicQuery.toLowerCase();
        return TALKS.stream()
                .filter(t -> t.title().toLowerCase().contains(q)
                        || t.category().toLowerCase().contains(q)
                        || t.speaker().toLowerCase().contains(q))
                .collect(Collectors.toList());
    }

    public static List<Talk> findByRoom(String room) {
        String q = room.toLowerCase();
        return TALKS.stream()
                .filter(t -> t.room().toLowerCase().contains(q))
                .collect(Collectors.toList());
    }

    public static List<Talk> getNextTalks(String day, String currentTime) {
        // Find the next time slot after currentTime
        return TALKS.stream()
                .filter(t -> t.day().equalsIgnoreCase(day))
                .filter(t -> t.time().compareTo(currentTime) > 0)
                .sorted((a, b) -> a.time().compareTo(b.time()))
                .limit(8) // Return up to 8 talks (one time slot)
                .collect(Collectors.toList());
    }

    public static List<Talk> getAllTalks() {
        return TALKS;
    }

    static String formatTalks(List<Talk> talks) {
        if (talks.isEmpty()) return "No talks found.";
        return talks.stream()
                .map(t -> "%s %s-%s | %s — %s (%s)".formatted(t.day(), t.time(), t.endTime(), t.title(), t.speaker(), t.room()))
                .collect(Collectors.joining("\n"));
    }
}
