package main;

import java.util.Objects;

public class Entry implements Comparable<Entry> {

    private Integer firstEmployeeId;
    private Integer secondEmployeeId;
    private Long maxDaysTogether;

    public Entry(Integer firstEmployeeId, Integer secondEmployeeId, Long maxDaysTogether) {
        this.firstEmployeeId = firstEmployeeId;
        this.secondEmployeeId = secondEmployeeId;
        this.maxDaysTogether = maxDaysTogether;
    }

    @Override
    public int compareTo(Entry o) {
        return Long.compare(this.maxDaysTogether, o.maxDaysTogether);
    }

    @Override
    public String toString() {
        return "Entry{" +
                "firstEmployeeId=" + firstEmployeeId +
                ", secondEmployeeId=" + secondEmployeeId +
                ", maxDaysTogether=" + maxDaysTogether +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(firstEmployeeId, entry.firstEmployeeId) && Objects.equals(secondEmployeeId, entry.secondEmployeeId) && Objects.equals(maxDaysTogether, entry.maxDaysTogether);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstEmployeeId, secondEmployeeId, maxDaysTogether);
    }
}
