package CalendarTasks;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Выберите пункт меню:");
            printMenu();
            if (scanner.hasNextInt()) {
                int menu = scanner.nextInt();
                while (menu != 0) {
                    switch (menu) {
                        case 1:
                            scanner.nextLine();
                            System.out.println("Введите название задачи");
                            String title = scanner.nextLine();
                            System.out.println("Введите тип задачи: WORK, PERSONAL");
                            String typeString = scanner.nextLine();
                            System.out.println("Введите описание задачи");
                            String description = scanner.nextLine();
                            System.out.println("Введите повторяемость задачи: ONE_TIME_TASK, YEARLY_TASK, DAILY_TASK, MONTHLY_TASK, WEEKLY_TASK");
                            String repeatabilityString = scanner.nextLine();
                            TasksService.addTask(title, typeString, description, repeatabilityString);
                            printMenu();
                            menu = scanner.nextInt();
                            break;
                        case 2:
                            System.out.println("Введите id задачи");
                            int idTaskForChangeTitle = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Введите новый заголовок задачи");
                            String titleNew = scanner.nextLine();
                            System.out.println(TasksService.updateTitle(idTaskForChangeTitle, titleNew));
                            printMenu();
                            menu = scanner.nextInt();
                            break;
                        case 3:
                            System.out.println("Введите id задачи");
                            int idTaskForChangeDescription = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Введите новое описание задачи");
                            String descriptionNew = scanner.nextLine();
                            System.out.println(TasksService.updateDescription(idTaskForChangeDescription, descriptionNew));
                            printMenu();
                            menu = scanner.nextInt();
                            break;
                        case 4:
                            System.out.println("Введите id задачи");
                            int idRemovingTask = scanner.nextInt();
                            TasksService.removeTask(idRemovingTask);
                            printMenu();
                            menu = scanner.nextInt();
                            break;
                        case 5:
                            System.out.println("Введите год, месяц и день задачи через пробел");
                            int year = scanner.nextInt();
                            int month = scanner.nextInt();
                            int day = scanner.nextInt();
                            LocalDate dateTasks = LocalDate.of(year, month, day);
                            System.out.println(TasksService.getAllByDate(dateTasks));
                            printMenu();
                            menu = scanner.nextInt();
                            break;
                        case 6:
                            System.out.println(TasksService.getRemoveTaskMap());
                            printMenu();
                            menu = scanner.nextInt();
                            break;
                        case 7:
                            System.out.println(TasksService.getAllGroupByDate());
                            printMenu();
                            menu = scanner.nextInt();
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println("1. Добавить задачу " +
                "\n2. Изменить заголовок задачи " +
                "\n3. Изменить описание задачи  " +
                "\n4. Удалить задачу " +
                "\n5. Получить задачи на указанный день " +
                "\n6. Получить архивные задачи " +
                "\n7. Получить сгруппированные по датам задачи " +
                "\n0. Выход");
    }
}