package CalendarTasks;


public enum Repeatability {
    ONE_TIME_TASK("Однократная"),
    YEARLY_TASK("Ежегодная"),
    DAILY_TASK("Ежедневная"),
    MONTHLY_TASK("Ежемесячная"),
    WEEKLY_TASK("Еженедельная");

    private final String repeatability;

    Repeatability(String repeatability) {
        this.repeatability = repeatability;
    }

    public String getRepeatability() {
        return repeatability;
    }

    @Override
    public String toString() {
        return "Повторяемость задачи: " + repeatability;
    }
}

