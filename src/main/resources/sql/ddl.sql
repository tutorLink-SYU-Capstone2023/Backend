# Data Definition Language: 데이터 정의어
# create, alter, drop, truncate 작성

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
    `post_reported_count`	INT	NOT NULL default 0
    #,foreign key (member_no) references `MEMBER` (member_no),
    #foreign key (category_code) references `BOARD_CATEGORY`(category_code)

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
    `member_no`	BIGINT	NOT NULL
    #,foreign key (post_num) references `POST`(post_num),
    #foreign key (member_no) references `MEMBER`(member_no)
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

