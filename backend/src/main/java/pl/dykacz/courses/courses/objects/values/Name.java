package pl.dykacz.courses.courses.objects.values;

import org.springframework.lang.NonNull;

public class Name {
    private final String name;

    public Name(@NonNull final String name){
        if(name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
