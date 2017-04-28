package hiatus.hiatusapp;

/**
 * Created by Cecile on 28/04/2017.
 */

public class CurrentIssue {

    public static class date {
        private int day;
        private int month;

        public date(int day, int month) {
            this.day = day;
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }
    }

    public static class Participative {

        private String type;
        private double time;
        private String theme;
        private String instructions;

        public Participative(String type, double time, String theme) {
            this.type = type;
            this.time = time;
            this.theme = theme;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getTime() {
            return time;
        }

        public void setTime(double time) {
            this.time = time;
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public String getInstructions() {
            return instructions;
        }

        public void setInstructions(String instructions) {
            this.instructions = instructions;
        }
    }

    private String[] themes;
    private date deadline;
    private Participative participative;

    public CurrentIssue(String[] themes, date deadline) {
        this.themes = themes;
        this.deadline = deadline;
        participative = null;
    }

    public String[] getThemes() {
        return themes;
    }

    public void setThemes(String[] themes) {
        this.themes = themes;
    }

    public date getDeadline() {
        return deadline;
    }

    public void setDeadline(date deadline) {
        this.deadline = deadline;
    }

    public Participative getParticipative() {
        return participative;
    }

    public void setParticipative(Participative participative) {
        this.participative = participative;
    }
}
