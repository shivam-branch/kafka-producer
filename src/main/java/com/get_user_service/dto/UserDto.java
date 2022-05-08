package com.get_user_service.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String key;

    private String name;

    private Boolean status;

}
