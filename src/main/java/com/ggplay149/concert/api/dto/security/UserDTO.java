package com.ggplay149.concert.api.dto.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String role;
    private String name;
    private String username;
}
