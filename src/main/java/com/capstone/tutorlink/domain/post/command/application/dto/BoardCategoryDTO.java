package com.capstone.tutorlink.domain.post.command.application.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardCategoryDTO {
    private String categoryCode;

    @NotNull(message = "카테고리명은 반드시 입력되어야 합니다.")
    private String categoryName;
}
