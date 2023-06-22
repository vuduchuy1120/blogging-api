package com.example.bloggingaplication.payloads;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Integer categoryId;
    @NotEmpty
    @Size(min = 1 , max = 200, message = "Category title must be min of 1 and max of 200 character!")
    private String categoryTitle;
    @Size(max = 5000)
    private String categoryDescription;

}
