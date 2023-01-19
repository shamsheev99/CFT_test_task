package bin.model;

public record IntegerData(int value) implements Sortable {

    @Override
    public int compareTo(Object o) {
        IntegerData other = (IntegerData) o;
        return value -  other.value();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
