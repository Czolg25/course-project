package pl.dykacz.courses.courses.objects.values;

public class Id {
    private final long id;

    public Id(final long id) {
        if(id < 0) throw new IllegalArgumentException("Id cannot be negative");

        this.id = id;
    }

    public long getIdAsLong() {
        return id;
    }

    @Override
    public String toString() {
        return this.id+"";
    }
}
