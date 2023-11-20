package com.capstone.tutorlink.domain.file.command.application.controller;

import com.capstone.tutorlink.domain.file.command.application.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileUploadController {
    @PostMapping("single-file")
    public String singleFileUpload(@RequestParam MultipartFile singleFile,
                                   String singleFileDescription, Model model) {
        System.out.println("singleFile : " + singleFile);
        System.out.println("singleFileDescription : " + singleFileDescription);
        /* 파일을 저장할 경로 설정 */
        String root = "src/main/resources/static";
        String filePath = root + "/uploadFiles";
        File dir = new File(filePath);
        System.out.println(dir.getAbsolutePath());
        if(!dir.exists()) {
            dir.mkdirs();
        }
        /* 파일명 변경 처리 */
        String originFileName = singleFile.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
        /* 파일을 저장 */
        try {
            singleFile.transferTo(new File(filePath + "/" + savedName));
            model.addAttribute("message", "파일을 정상적으로 업로드하였습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "파일 업로드를 실패하였습니다. 잠시 후 다시 시도해주세요.");
        }
        return "result";
    }

    @PostMapping("multi-file")
    public String multiFileUpload(@RequestParam List<MultipartFile> multiFiles,
                                  String multiFileDescription, Model model) {
        System.out.println("multiFiles : " + multiFiles);
        System.out.println("multiFileDescription : " + multiFileDescription);
        /* 파일을 저장할 경로 설정 */
        String root = "src/main/resources/static";
        System.out.println("root : " + root);
        String filePath = root + "/uploadFiles";
        File dir = new File(filePath);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        List<FileDTO> files = new ArrayList<>();
        try {
            for(MultipartFile file : multiFiles) {
                /* 파일명 변경 처리 */
                String originFileName = file.getOriginalFilename();
                String ext = originFileName.substring(originFileName.lastIndexOf("."));
                String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
                /* 파일에 관한 정보 추출 후 보관 */
                files.add(new FileDTO(originFileName, savedName, filePath,
                        multiFileDescription));
                /* 파일을 저장 */
                file.transferTo(new File(filePath + "/" + savedName));
            }
            model.addAttribute("message", "모든 파일을 정상적으로 업로드하였습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            /* 실패 시 이전에 저장 된 파일 삭제 */
            for(FileDTO file : files) {
                new File(filePath + "/" + file.getSavedName()).delete();
            }
            model.addAttribute("message", "파일 업로드를 실패하였습니다. 잠시 후 다시 시도해주세요.");
        }
        return "result";
    }
}
