package com.trust.rms.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Integer roleId;
    private String roleName;
    private String status;

}
