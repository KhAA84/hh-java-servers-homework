package dto;

import java.time.LocalDateTime;

public class CounterDto {
    private final LocalDateTime date;
    private final int counter;

    public CounterDto(LocalDateTime date, int counter)
    {
        this.date = date;
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }

    public LocalDateTime getDate() {
        return date;
    }
}

