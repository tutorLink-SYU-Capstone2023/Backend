package com.capstone.tutorlink.domain.file.command.application.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
    private String originFileName;
    private String savedName;
    private String filePath;
    private String fileDescription;

}
