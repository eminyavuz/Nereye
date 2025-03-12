package com.emin.nereye.dto.userDto;

import com.emin.nereye.enumeration.Role;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {
    private  String user_name;
    private String password;
    private  String email;
    private String phone_number;
    private String  first_name;
    private  String last_name;
    private Role role;

}
