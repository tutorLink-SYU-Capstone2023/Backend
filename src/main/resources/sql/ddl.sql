# Data Definition Language: 데이터 정의어
# create, alter, drop, truncate 작성


#DROP TABLE IF EXISTS `MEMBER`;
CREATE TABLE `MEMBER` (
                          `member_no`	bigint	NOT NULL,
                          `member_id`	VARCHAR(20)	NOT NULL,
                          `member_pw`	VARCHAR(100)	NOT NULL,
                          `member_nickname`	VARCHAR(45)	NOT NULL,
                          `member_name`	VARCHAR(45)	NOT NULL,
                          `member_email`	VARCHAR(45)	NOT NULL,
                          `member_gender`	CHAR(1)	NOT NULL,
                          `member_birthday`	DATE	NOT NULL,
                          `enroll_date datetime`	VARCHAR(255)	NOT NULL,
                          `member_status`	CHAR(1)	NULL,
                          `member_phone_number`	INT(11)	NOT NULL,
                          `tutor_school_authorize`	CHAR(1)	NOT NULL,
                          `tutor_midleschool`	VARCHAR(45)	NULL,
                          `tutor_highschool`	VARCHAR(45)	NULL,
                          `tutor_uni`	VARCHAR(45)	NULL,
                          `tutor_uni_is_enrolled`	CHAR(1)	NULL,
                          `tutor_major`	VARCHAR(45)	NULL,
                          `tutor_major_num`	INT	NULL,
                          `tutor_authorize`	VARCHAR(45)	NULL,
                          `Key`	VARCHAR(255)	NOT NULL
);

ALTER TABLE `MEMBER` ADD CONSTRAINT `PK_MEMBER` PRIMARY KEY (
                                                             `member_no`
    );
#게시판 카테고리
#DROP TABLE IF EXISTS `BOARD_CATEGORY`;
CREATE TABLE `BOARD_CATEGORY` (
                                  `category_code`	ENUM('CATEGORY001', 'CATEGORY002', 'CATEGORY003', 'CATEGORY004', 'CATEGORY005')	PRIMARY KEY,
                                  `category_name`	VARCHAR(50)	NOT NULL
);

# 게시글
#DROP TABLE IF EXISTS `POST`;
CREATE TABLE `POST` (
    `post_num`	varchar(36) default (uuid()) PRIMARY KEY,
    `member_no`	BIGINT	NULL,
    `category_code`	ENUM('CATEGORY001', 'CATEGORY002', 'CATEGORY003', 'CATEGORY004', 'CATEGORY005')	NOT NULL,
    `post_title`	VARCHAR(45)	NOT NULL,
    `post_content`	LONGTEXT	NOT NULL,
    `post_is_deleted`	ENUM('Y', 'N')	NOT NULL,
    `post_regist_date`	DATETIME	NOT NULL,
    `post_update_date`	DATETIME	NULL,
    `post_delete_date`	DATETIME	NULL,
    `post_count`	INT	NOT NULL default 0,
    `post_reported_count`	INT	NOT NULL default 0,
    foreign key (member_no) references `MEMBER` (member_no),
    foreign key (category_code) references `BOARD_CATEGORY`(category_code)

);

#게시글 댓글
#DROP TABLE IF EXISTS `COMMENT`;
CREATE TABLE `COMMENT` (
    `commnet_id`	VARCHAR(36) DEFAULT (UUID()) PRIMARY KEY ,
    `comment_content`	MEDIUMTEXT	NOT NULL,
    `comment_regist_date`	DATETIME	NOT NULL,
    `comment_update_date`	DATETIME	NULL,
    `comment_delete_date`	DATETIME	NULL,
    `commnet_is_deleted` ENUM('Y', 'N') NOT NULL,
    `comment_report_cumulated_num`	INT DEFAULT 0 NOT NULL,
    `post_num`	varchar(36) default (uuid()) NOT NULL ,
    `member_no`	BIGINT	NOT NULL,
    foreign key (post_num) references `POST`(post_num),
    foreign key (member_no) references `MEMBER`(member_no)
);

#DROP TABLE IF EXISTS `BOARD_ATTACHED_FILE`;
CREATE TABLE `BOARD_ATTACHED_FILE` (
                                       `file_id`	VARCHAR(36) DEFAULT uuid() PRIMARY KEY ,
                                       `file_name`	VARCHAR(100)	NOT NULL,
                                       `file_regist_datetime`	DATETIME	NOT NULL,
                                       `file_information`	VARCHAR(100)	NOT NULL,
                                       `file_locate`	VARCHAR(100)	NOT NULL,
                                       `post_num`	INT	NOT NULL
);

