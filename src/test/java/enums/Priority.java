package enums;

import lombok.Getter;

public enum Priority {
    HIGH("!!!"),
    MEDIUM("!!"),
    LOW("!");

    @Getter
    private final String priority;

    Priority(String priority) {
        this.priority = priority;
    }
}
