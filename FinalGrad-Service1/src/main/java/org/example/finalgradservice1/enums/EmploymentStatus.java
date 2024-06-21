package org.example.finalgradservice1.enums;

public enum EmploymentStatus {
    FULL_TIME(1),
    PART_TIME(2),
    REMOTELY(3),
    TERMINATED(4);

    private final int id;

    EmploymentStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EmploymentStatus fromId(int id) {
        for (EmploymentStatus status : EmploymentStatus.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + id);
    }
}

