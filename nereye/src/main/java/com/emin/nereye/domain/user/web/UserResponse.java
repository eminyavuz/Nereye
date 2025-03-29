package com.emin.nereye.domain.user.web;
import com.emin.nereye.enumeration.Role;
import com.emin.nereye.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse extends BaseResponse {
    private String user_name;
    private String first_name;
    private String last_name;
    private String user_email;
    private Role role;



}
