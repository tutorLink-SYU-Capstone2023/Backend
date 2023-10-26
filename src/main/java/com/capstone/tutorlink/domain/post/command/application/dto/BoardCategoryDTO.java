package com.capstone.tutorlink.domain.post.command.application.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardCategoryDTO {
    private String categoryCode;
    private String categoryName;
}
