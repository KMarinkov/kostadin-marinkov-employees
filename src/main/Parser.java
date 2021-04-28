package main;

import java.util.*;

public class Parser {

    //  ProjectId<EmployeeId, DaysTogether>
    private Map<Integer, Map<Integer, Long>> employeeIdWithMap;

    private Map<Integer, Map<Integer, Date>> projectIdWithMap;
    private Set<Entry> entrySet;


    public Parser() {
        this.employeeIdWithMap = new LinkedHashMap<>();
        this.entrySet = new HashSet<>();
    }

    public void setData(FileReader fileReader) {
        this.projectIdWithMap = fileReader.start()
                .orElseThrow(() -> new IllegalStateException());
    }

    public void parseData() {

        //Calculate max days together and populate 
        for (Map<Integer, Date> employeeIdAndDate : projectIdWithMap.values()) {

            int y = 0;
            int x = 0;

            for (var entry1 : employeeIdAndDate.entrySet()) {

                if (!employeeIdWithMap.containsKey(entry1.getKey())) {
                    employeeIdWithMap.put(entry1.getKey(), new HashMap<>());
                }

                x++;
                y = x;

                for (var entry2 : employeeIdAndDate.entrySet()) {
                    if (y > 0) {
                        y--;
                        continue;
                    }

                    if (entry1.equals(entry2)) {
                        continue;
                    }

                    if (!employeeIdWithMap.get(entry1.getKey()).containsKey(entry2.getKey())) {
                        employeeIdWithMap.get(entry1.getKey()).
                                put(entry2.getKey(), entry1.getValue().getDays(entry2.getValue()));
                    } else {
                        Long days = employeeIdWithMap.get(entry1.getKey()).get(entry2.getKey());
                        employeeIdWithMap.get(entry1.getKey()).
                                put(entry2.getKey(), days + entry1.getValue().getDays(entry2.getValue()));
                    }
                }
            }
        }
    }

    public void populateEntrySet() {

        Integer id1 = 0;
        Integer id2 = 0;
        Long maxDays = 0L;

        // Search for the pair with max days together and filter duplicates via HashSet
        for (var entries : employeeIdWithMap.entrySet()) {

            for (var entries2 : entries.getValue().entrySet()) {
                if (entries2.getValue() >= maxDays) {

                    maxDays = entries2.getValue();
                    id1 = entries.getKey();
                    id2 = entries2.getKey();
                }
            }

            addEntry(new Entry(id1, id2, maxDays));
        }
    }

    private void addEntry(Entry entry) {
        if (!entrySet.isEmpty()) {
            Entry entry1 = entrySet.stream()
                    .findFirst().get();

            int difference = entry.compareTo(entry1);

            if (difference > 0) {
                entrySet.clear();
                entrySet.add(entry);
            } else if (difference == 0) {
                entrySet.add(entry);
            }
        } else {
            entrySet.add(entry);
        }
    }

    public void printResult() {
        for (var entry : entrySet) {
            System.out.println(entry);
        }
    }
}
