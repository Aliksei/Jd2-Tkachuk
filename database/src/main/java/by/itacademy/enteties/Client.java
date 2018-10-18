package by.itacademy.enteties;


import lombok.*;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@Builder
public class Client {

    private Integer id;
    @NonNull
    private String firstName;
    @NonNull
    private String secondName;
    @NonNull
    private int age;

}
