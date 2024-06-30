package Java.finalWork;

import java.util.*;

public class Filter {
    public static void main(String[] args) {
        // Создадим множество ноутбуков
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Model1", 2, 512, "Windows", "Black"));
        laptops.add(new Laptop("Model2", 4, 256, "Windows", "Silver"));
        laptops.add(new Laptop("Model3", 6, 1024, "Linux", "Black"));
        laptops.add(new Laptop("Model4", 8, 512, "MacOS", "White"));
        laptops.add(new Laptop("Model5", 16, 256, "Windows", "Red"));
        laptops.add(new Laptop("Model6", 32, 512, "Linux", "Silver"));
        laptops.add(new Laptop("Model7", 64, 1024, "Windows", "Black"));
        laptops.add(new Laptop("Model8", 2, 128, "MacOS", "White"));
        laptops.add(new Laptop("Model9", 4, 256, "Linux", "Black"));
        laptops.add(new Laptop("Model10", 6, 512, "Windows", "Blue"));
        laptops.add(new Laptop("Model11", 8, 1024, "Linux", "Red"));
        laptops.add(new Laptop("Model12", 16, 2048, "MacOS", "Silver"));
        laptops.add(new Laptop("Model13", 32, 128, "Windows", "Black"));
        laptops.add(new Laptop("Model14", 64, 256, "Linux", "White"));
        laptops.add(new Laptop("Model15", 128, 512, "Windows", "Red"));
        laptops.add(new Laptop("Model16", 64, 1024, "MacOS", "Black"));
        laptops.add(new Laptop("Model17", 32, 2048, "Linux", "Silver"));
        laptops.add(new Laptop("Model18", 16, 256, "Windows", "Blue"));
        laptops.add(new Laptop("Model19", 8, 512, "MacOS", "White"));
        laptops.add(new Laptop("Model20", 16, 128, "Linux", "Black"));

        // Хранение доступных критериев фильтрации
        Map<Integer, String> criteriaMap = new HashMap<>();
        criteriaMap.put(1, "ram");
        criteriaMap.put(2, "hdd");
        criteriaMap.put(3, "os");
        criteriaMap.put(4, "color");

        Scanner scanner = new Scanner(System.in);
        // Хранение параметров фильтрации
        Map<String, String> filters = new HashMap<>();

        // Запрос критериев фильтрации у пользователя
        while (true) {
            try {
                // Вывод текущих выбранных критериев
                if (!filters.isEmpty()) {
                    System.out.println("Выбранные критерии:");
                    for (Map.Entry<String, String> entry : filters.entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                }
                // Вывод доступных критериев фильтрации
                System.out.println("Введите цифру, соответствующую необходимому критерию:");
                System.out.println("1 - ОЗУ");
                System.out.println("2 - Объем ЖД");
                System.out.println("3 - Операционная система");
                System.out.println("4 - Цвет");

                int criterion = scanner.nextInt();
                if (criteriaMap.containsKey(criterion)) {
                    String criterionKey = criteriaMap.get(criterion);
                    System.out.println("Введите значение для " + criterionKey + ":");
                    String value = scanner.next();
                    filters.put(criterionKey, value);
                } else {
                    System.out.println("Неверный критерий. Повторите ввод.");
                }

                // Запрос на продолжение ввода критериев
                System.out.println("Продолжить ввод критериев? (да/нет)");
                String continueInput = scanner.next();
                if (continueInput.equalsIgnoreCase("нет")) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка ввода: " + e.getMessage());
                scanner.nextLine(); // очистка сканера
            }
        }
        scanner.close();

        // Фильтрация ноутбуков по введенным критериям
        Set<Laptop> filteredLaptops = filterLaptops(laptops, filters);
        if (filteredLaptops.isEmpty()) {
            System.out.println("Нет ноутбуков, соответствующих указанным критериям.");
        } else {
            System.out.println("Ноутбуки, соответствующие критериям:");
            for (Laptop laptop : filteredLaptops) {
                System.out.println(laptop);
            }
        }
    }

    // Метод для фильтрации ноутбуков по введенным критериям
    public static Set<Laptop> filterLaptops(Set<Laptop> laptops, Map<String, String> filters) {
        Set<Laptop> filteredLaptops = new HashSet<>(laptops);

        // Применение каждого фильтра
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            String key = filter.getKey();
            String value = filter.getValue();

            switch (key) {
                case "ram":
                    int ram = Integer.parseInt(value);
                    filteredLaptops.removeIf(laptop -> laptop.ram != ram);
                    break;
                case "hdd":
                    int hdd = Integer.parseInt(value);
                    filteredLaptops.removeIf(laptop -> laptop.hdd != hdd);
                    break;
                case "os":
                    filteredLaptops.removeIf(laptop -> !laptop.os.equalsIgnoreCase(value));
                    break;
                case "color":
                    filteredLaptops.removeIf(laptop -> !laptop.color.equalsIgnoreCase(value));
                    break;
            }
        }
        return filteredLaptops;
    }
}
