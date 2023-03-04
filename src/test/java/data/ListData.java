package data;



import lombok.Getter;

import java.util.Objects;

public class ListData {
    @Getter
    private String name;
    @Getter
    private String color;

    public ListData(String name, String color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ListData listData = (ListData) obj;
        return Objects.equals(name, listData.name) && Objects.equals(color, listData.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }
}
