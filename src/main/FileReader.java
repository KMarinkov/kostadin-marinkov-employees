package main;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class FileReader {

    private Map<Integer, Map<Integer, Date>> map;
    private final String COMMA_DELIMITER = ",";

    public FileReader() {
        this.map = new HashMap<>();
    }

    public Optional<Map<Integer, Map<Integer, Date>>> start() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter file path(empty to exit): ");
        String line = scanner.nextLine().trim();

        return readFile(line);
    }

    private Optional<Map<Integer, Map<Integer, Date>>> readFile(String path) {

        try (Scanner scanner = new Scanner(Paths.get(path))) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(COMMA_DELIMITER);

                Integer employeeId = Integer.valueOf(parts[0].trim());
                Integer projectId = Integer.valueOf(parts[1].trim());

                LocalDate dateFrom = parseDate(parts[2].trim());
                LocalDate dateTo = parseDate(parts[3].trim());

                if (!map.containsKey(projectId)) {
                    map.put(projectId, new HashMap<>());
                }

                map.get(projectId).put(employeeId, new Date(dateFrom, dateTo));

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return Optional.of(map);
    }

    private LocalDate parseDate(String s) {
        if (s.equals("NULL")) {
            return LocalDate.now();
        }

        String[] date = s.trim().split("-");

        int year = Integer.valueOf(date[0]);
        int month = Integer.valueOf(date[1]);
        int day = Integer.valueOf(date[2]);

        return LocalDate.of(year, month, day);
    }
}
