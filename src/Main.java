public class Main {
    enum Level { LOW, MEDIUM, HIGH }

    public static void main(String[] args) {
        assess("Email delay for one user", Level.LOW, Level.MEDIUM);
        assess("CRM unavailable for sales team", Level.HIGH, Level.HIGH);
        assess("Payroll report slow", Level.MEDIUM, Level.MEDIUM);
    }

    static void assess(String incident, Level impact, Level urgency) {
        String priority = priority(impact, urgency);
        String target = switch (priority) {
            case "P1" -> "15 minutes";
            case "P2" -> "1 hour";
            case "P3" -> "4 hours";
            default -> "1 business day";
        };
        System.out.printf("%-32s impact=%-6s urgency=%-6s -> %s, response target %s%n",
                incident, impact, urgency, priority, target);
    }

    static String priority(Level impact, Level urgency) {
        int score = impact.ordinal() + urgency.ordinal();
        if (impact == Level.HIGH && urgency == Level.HIGH) return "P1";
        if (score >= 3) return "P2";
        if (score >= 2) return "P3";
        return "P4";
    }
}
