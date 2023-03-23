package CalendarTasks;

public enum Type {
    WORK("Рабочая"),
    PERSONAL("Личная");
    private final String typeTask;
    Type(String typeTask) {
        this.typeTask = typeTask;
    }

    @Override
    public String toString() {
        return "Тип задачи: " + typeTask;
    }

    public String getTypeTask() {
        return typeTask;
    }
}
