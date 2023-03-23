package CalendarTasks;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TasksService {
    private static final Map<Integer, Tasks> tasksMap = new HashMap<>();
    private static final Map<Integer, Tasks> removeTaskMap = new HashMap<>();

    public static void addTask(String title, String typeString, String description, String repeatabilityString) throws IncorrectArgumentException {
        Boolean typeValid = typeString.matches("(WORK|PERSONAL)");
        Boolean repeatabilityValid = repeatabilityString.matches("(ONE_TIME_TASK|YEARLY_TASK|DAILY_TASK|MONTHLY_TASK|WEEKLY_TASK)");
        if (typeValid & repeatabilityValid) {
            Tasks task = new Tasks(title, Type.valueOf(typeString), description, Repeatability.valueOf(repeatabilityString));
            tasksMap.put(task.getId(), task);
        } else {
            throw new IncorrectArgumentException("Ошибка, введены некорректные данные");
        }
    }

    public static void removeTask(int id) throws TaskNotFoundException {
        if (tasksMap.containsKey(id)) {
            removeTaskMap.put(id, tasksMap.get(id));
            tasksMap.remove(id);
        } else {
            throw new TaskNotFoundException("Задача не найдена");
        }
    }

    public static Map<Integer, Tasks> getTasksMap() {
        return tasksMap;
    }

    public static Map<Integer, Tasks> getRemoveTaskMap() {
        return removeTaskMap;
    }

    public static Tasks updateDescription(int id, String description) throws TaskNotFoundException{
        if (tasksMap.containsKey(id)) {
            tasksMap.get(id).setDescription(description);
        } else {
            throw new TaskNotFoundException("Задача не найдена");
        }
        return tasksMap.get(id);
    }

    public static Tasks updateTitle(int id, String title) throws TaskNotFoundException{
        if (tasksMap.containsKey(id)) {
            tasksMap.get(id).setTitle(title);
        } else {
            throw new TaskNotFoundException("Задача не найдена");
        }
        return tasksMap.get(id);
    }

    public static Map<LocalDate, List<Tasks>> getAllGroupByDate () {
        Map<LocalDate, List<CalendarTasks.Tasks>> groupByDateMap = tasksMap.values().stream()
                .collect(Collectors.groupingBy(CalendarTasks.Tasks::getDate,Collectors.toList()));
        return groupByDateMap;



    }

    public static Map<Integer, Tasks> getAllByDate(LocalDate date) {
        Map<Integer, Tasks> resultMap = tasksMap.entrySet().stream()
                .filter(entry -> entry.getValue().predicate(date))
                .collect(Collectors.toMap(Map.Entry :: getKey, Map.Entry :: getValue));
        return resultMap;
    }

}
