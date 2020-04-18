package academy.learnprogramming.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Data //setters and getters
@EqualsAndHashCode(of = "id")
public class TodoItem {

    // == fields ==
    private int id;
    private String title;
    private String details;
    private LocalDate deadline;

    // == constructor ==
    public TodoItem(String title, String details, LocalDate deadLine) {
        this.title = title;
        this.details = details;
        this.deadline = deadLine;
    }


}