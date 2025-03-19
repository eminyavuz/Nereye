package com.emin.nereye.domain.user.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
private String user_name;
private String first_name;
private String last_name;
private String  password;
private  String email;

}
