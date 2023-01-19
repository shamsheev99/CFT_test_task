package bin.model;

public record StringData(String value) implements Sortable{

    @Override
    public int compareTo(Object o) {
        StringData other = (StringData)  o;
        return value.compareTo(other.value());
    }

    @Override
    public String toString() {
        return  value;
    }
}
