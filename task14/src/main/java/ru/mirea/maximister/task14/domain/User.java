package ru.mirea.maximister.task14.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
}
