package CalendarTasks;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Objects;

public class Tasks {
    private static int countId;
    private String title;
    private final Type type;
    private final int id;
    private final LocalDateTime dateTime;
    private String description;
    private final Repeatability repeatability;

    public Tasks(String title, Type type, String description, Repeatability repeatability) {
        countId++;
        this.title = title;
        this.type = type;
        this.id = countId;
        this.dateTime = LocalDateTime.now();
        this.description = description;
        this.repeatability = repeatability;
    }

    public String getTitle() {
        return title;
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public Repeatability getRepeatability() {
        return repeatability;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tasks tasks = (Tasks) o;
        return id == tasks.id && title.equals(tasks.title) && type == tasks.type && dateTime.equals(tasks.dateTime) && description.equals(tasks.description) && repeatability == tasks.repeatability;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type, id, dateTime, description, repeatability);
    }

    @Override
    public String toString() {
        return "Задача №" + id + " " + title +
                "\n" + type +
                "\nОписание задачи: " + description +
                "\n" + repeatability +
                "\nДата создания: " + dateTime;
    }

    public LocalDate nextOccurrenceOfTheDate() {
        LocalDate resultDate = dateTime.toLocalDate();
        switch (repeatability) {
            case DAILY_TASK:
                resultDate = resultDate.plusDays(1);
                break;
            case WEEKLY_TASK:
                resultDate = resultDate.plusWeeks(1);
                break;
            case YEARLY_TASK:
                resultDate = resultDate.plusYears(1);
                break;
            case MONTHLY_TASK:
                resultDate = resultDate.plusMonths(1);
                break;
        }
        return resultDate;
    }

    public boolean appearsln(LocalDate date) {
        Period d = Period.between(date, dateTime.toLocalDate());
        boolean b = false;
        switch (repeatability) {
            case DAILY_TASK:
                b = true;
                break;
            case WEEKLY_TASK:
                b = date.getDayOfWeek() == dateTime.getDayOfWeek();
                break;
            case YEARLY_TASK:
                b = d.getDays() == 0 & d.getMonths() == 0 ;
                break;
            case MONTHLY_TASK:
                b = d.getDays() == 0 ;
                break;
            case ONE_TIME_TASK:
                b = (date.compareTo(this.getDate())== 0);
                break;
        }
        return b;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public Boolean predicate(LocalDate date) {
        return (this.appearsln(date) & date.compareTo(this.getDate())>=0);
    }
}

