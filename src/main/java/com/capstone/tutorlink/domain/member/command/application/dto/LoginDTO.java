package com.capstone.tutorlink.domain.member.command.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginDTO {
    String username;
    String password;
}
