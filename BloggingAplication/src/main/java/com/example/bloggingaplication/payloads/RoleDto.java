package com.example.bloggingaplication.payloads;

import lombok.*;

@Data
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Integer id;
    private String roleName;
}
