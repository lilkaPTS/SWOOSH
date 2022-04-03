package com.SWOOSH.dto;

import com.SWOOSH.enums.Role;
import com.SWOOSH.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserFullDto extends UserDto {

    private String name;
    private Role role;
    private Status status;
}
