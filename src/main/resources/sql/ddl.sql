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

-- auto-generated definition
create table ACCEPTEDTYPE_CATEGORY
(
    my_key char(10)    not null
        primary key,
    Field  varchar(10) not null
);
-- auto-generated definition
create table UNIVERSITY
(
    univ_code     varchar(20) not null
        primary key,
    univ_name     varchar(50) null,
    category_code varchar(10) null
);
-- auto-generated definition
create table member_role
(
    member_role_code int auto_increment
        primary key,
    authority_num    int null,
    member_no        int null,
    constraint FK_AUTHORITY_TO_MEMBER_ROLE_1
        foreign key (authority_num) references AUTHORITY (authority_num),
    constraint FK_MEMBER_TO_MEMBER_ROLE_1
        foreign key (member_no) references member (member_no),
    constraint member_role_ibfk_1
        foreign key (authority_num) references AUTHORITY (authority_num),
    constraint FK9b3yvy4p9bhays8diipd7qsj3
        foreign key (authority_num) references AUTHORITY (authority_num),
    constraint member_role_ibfk_2
        foreign key (member_no) references member (member_no),
    constraint FKr4x8609l32oouja8cpn0rujhp
        foreign key (member_no) references member (member_no)
);





-- auto-generated definition
create table AUTHORITY
(
    authority_num  int auto_increment
        primary key,
    authority_name varchar(45)   not null,
    authority_desc varchar(4000) not null
);
-- auto-generated definition
create table member
(
    member_no              int auto_increment
        primary key,
    member_id              varchar(20)                          not null,
    member_pw              varchar(100)                         not null,
    member_nickname        varchar(45)                          not null,
    member_name            varchar(45)                          not null,
    member_email           varchar(100)                         not null,
    member_gender          char                                 not null,
    member_birthday        date                                 not null,
    member_enroll_date     datetime   default CURRENT_TIMESTAMP not null,
    member_current_status  varchar(2) default 'A'               not null,
    member_phone_number    varchar(20)                          not null,
    tutor_school_authorize char       default 'N'               not null,
    tutor_middle_school    varchar(45)                          null,
    tutor_high_school      varchar(45)                          null,
    tutor_uni              varchar(45)                          null,
    tutor_uni_is_enrolled  char                                 null,
    tutor_major            varchar(45)                          null,
    tutor_major_num        double                               null,
    tutor_authorize        varchar(45)                          null,
    my_key                 char(10)                             null,
    constraint fk_member_my_key
        foreign key (my_key) references ACCEPTEDTYPE_CATEGORY (my_key),
    constraint FKf58e132r60d5ux99si5oof12o
        foreign key (my_key) references ACCEPTEDTYPE_CATEGORY (my_key),
    constraint fk_member_tutor_uni
        foreign key (tutor_uni) references UNIVERSITY (univ_code),
    constraint FKbfepklb4jp1yplod1quq2iwe4
        foreign key (tutor_uni) references UNIVERSITY (univ_code),
    check (`member_gender` in (_utf8mb4\'M\',_utf8mb4\'F\')),
	check (`member_current_status` in (_utf8mb4\'A\',_utf8mb4\'D\')),
	check (`tutor_school_authorize` in (_utf8mb4\'Y\',_utf8mb4\'N\')),
	check (`tutor_uni_is_enrolled` in (_utf8mb4\'Y\',_utf8mb4\'N\'))
);




-- auto-generated definition
create table LIKED_MEMBERS
(
    id              int auto_increment
        primary key,
    member_id       int not null,
    liked_member_id int not null,
    constraint member_id
        unique (member_id, liked_member_id),
    constraint liked_members_ibfk_1
        foreign key (member_id) references member (member_no),
    constraint liked_members_ibfk_2
        foreign key (liked_member_id) references member (member_no)
);

create index liked_member_id
    on LIKED_MEMBERS (liked_member_id);



