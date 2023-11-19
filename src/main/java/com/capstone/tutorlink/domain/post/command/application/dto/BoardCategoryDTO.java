package com.capstone.tutorlink.domain.post.command.application.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardCategoryDTO {
    @NotNull(message = "카테고리 코드는 비어있을 수 없습니다.")
    private String categoryCode;
    @NotNull(message = "카테고리는 반드시 입력되다어야 합니다.")
    private String categoryName;
}
