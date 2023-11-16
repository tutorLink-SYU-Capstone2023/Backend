#Data Manipulation Language
#select, insert, update, delete 작성

# 게시판 카테고리 insert
INSERT INTO BOARD_CATEGORY(category_code, category_name)
VALUES ('CATEGORY001', '잡담'),
       ('CATEGORY002', '자료공유'),
       ('CATEGORY003', '입시꿀팁'),
       ('CATEGORY004', '멘토캐스트'),
       ('CATEGORY005','질문글');
select * from BOARD_CATEGORY;

#게시글 임시값 insert
INSERT INTO POST(member_no, category_code, post_title, post_content, post_is_deleted, post_regist_date, post_update_date, post_delete_date)
VALUES(10000001, 'CATEGORY001', '2023생윤 자료 공유해요', '직접 제작한 생윤 모의고사 2회차 입니다. 답지와 함께 첨부했으니 풀어보세요!', 'N', '2023-10-18 03:27:33', NULL, NULL),
      (10000002, 'CATEGORY004', '수능 자료 제작하느라 너무 힘들었네요','모의고사 2회 제작이 이렇게 오래 걸리는 일인지 몰랐습니다! 그래도 뿌듯하네요', 'N', '2023-10-18 3:33:23', '2023-10-18 3:58:01', NULL),
      (10000211, 'CATEGORY004', '사문 VS 윤사 뭐 선택해야 할까요', '예비 고3인데 윤리과목이 재밌어요. 근데 다른 친구들은 생윤, 사문을 많이 해서..', 'Y', '2023-10-18 14:23:11', NULL, '2023-10-18 20:22:11'),
      (10001249, 'CATEGORY004', '금천구 국어 선생님 추천 받아요 ^^', '지인 소개로 수학 선생님은 구했는데 국어 선생님이 필요할 것 같아요', 'N', '2023-10-19 16:23:11', NULL, NULL),
      (10000092, 'CATEGORY003', '3개월만에 영어 6->2 가능합니다.', '제 방식을 따라 온 친구들은 ..', 'N', '2023-10-20 19:11:45', NULL, NULL);
select * from POST;

INSERT INTO COMMENT(comment_content, comment_regist_time, comment_is_deleted, comment_report_cumulated_num, comment_delete, post_num, member_no)
VALUES ('감사합니다', '2023-10-31 14:22:21', 'N', 0, NULL, '2db2c1b8-7e4b-11ee-8272-4bc2e949b19f', 10000002),
       ('네~ 열공하세요', '2023-10-31 14:28:21', 'N', 0, NULL, '2db2c1b8-7e4b-11ee-8272-4bc2e949b19f', 10000001),
       ('네~ 열공하세요', '2023-11-01 16:28:21', 'N', 0, NULL, '2db2c1b8-7e4b-11ee-8272-4bc2e949b19f', 10000001),
       ('네~ 열공하세요', '2023-11-01 20:58:21', 'Y', 0, '2023-11-01 21:05:36', '2db2c1b8-7e4b-11ee-8272-4bc2e949b19f', 10000001);
select * from COMMENT;

#합격전형카테고리
INSERT INTO ACCEPTEDTYPE_CATEGORY (my_key, Field)
VALUES  (1, '정시'),
        (2, '수시-학종'),
        (3, '수시-교과'),
        (4, '적성'),
        (5, '논술');

#권한 정보
INSERT INTO `AUTHORITY` (authority_name,authority_desc)
VALUES  (1,'관리자'),
        (2,'튜티'),
        (3,'튜터');

#멤버 권한
INSERT INTO MEMBER_ROLE (authority_num,member_id)
VALUES     (1,'10000000'),
           (2,'10000001'),
           (2,'10000002'),
           (3,'10000003'),
           (3,'10000004'),
           (3,'10000005'),
           (2,'10000006');

#선호지역
INSERT INTO PREFER_REGION (my_key, region_code, authority_num, member_no)
VALUES      ('', 'GANGDONG', 2 , '10000001'),
            ('', 'SONGPA', 2 , '10000002'),
            ('', 'GANGNAM', 3 , '10000001'),
            ('', 'SECHO', 2 , '10000003');

#과목대분류
INSERT INTO SUBJECT_MAINCATEGORY (subject_code, subject_name)
VALUES      ('sub001', '국어'),
            ('sub002', '수학'),
            ('sub003', '영어'),
            ('sub004', '사회'),
            ('sub005', '과학');

#과목소분류
INSERT INTO SUBJECT_SUBCATEGORY (my_key, Field, subject_code)
VALUES
    ('sub001', 'subd001', '문법'),
    ('sub001', 'subd002', '비문학'),
    ('sub001', 'subd003', '문학'),
    ('sub002', 'subd001', '수1'),
    ('sub002', 'subd002', '수2'),
    ('sub002', 'subd003', '확률과 통계'),
    ('sub002', 'subd004', '미적분'),
    ('sub002', 'subd005', '기하와 벡터');

#지역대분류

INSERT INTO REGION (main_region_code, main_region_name)
VALUES
    ('1', '서울특별시'),
    ('2', '부산광역시'),
    ('3', '대구광역시'),
    ('4', '인천광역시'),
    ('5', '광주광역시'),
    ('6', '대전광역시'),
    ('7', '울산광역시'),
    ('8', '경기도'),
    ('9', '강원도'),
    ('10', '충청북도'),
    ('11', '충청남도'),
    ('12', '전라북도'),
    ('13', '전라남도'),
    ('14', '경상북도'),
    ('15', '경상남도'),
    ('16', '제주특별자치도');

#지역중분류

INSERT INTO REGION_DETAIL (sub_region_code, main_region_code, sub_region_name)
VALUES    ('INCHEON', 'GANGHWA', '강화군'),    ('INCHEON', 'SEO-GU', '서구'),    ('INCHEON', 'JUNG-GU', '중구'),    ('INCHEON', 'DONG-GU', '동구'),    ('INCHEON', 'ONGJIN', '옹진군'),    ('INCHEON', 'GWEWANG', '계양구'),    ('INCHEON', 'BUPUNG', '부평구'),    ('INCHEON', 'MICHUHOL', '미추홀구'),    ('INCHEON', 'NAMDONG', '남동구'),    ('INCHEON', 'YEONSU', '연수구'),    ('GYEONGGI', 'EN_YEONCHEON', '연천군'),    ('GYEONGGI', 'EN_POCHEON', '포천시'),    ('GYEONGGI', 'EN_DONGDUCHON', '동두천시'),    ('GYEONGGI', 'EN_YANGJU', '양주시'),    ('GYEONGGI', 'EN_', '의정부시'),    ('GYEONGGI', 'EN_NAMYANGJU', '남양주시'),    ('GYEONGGI', 'EN_GURI', '구리시'),    ('GYEONGGI', 'EN_GAPYEONG', '가평군'),    ('GYEONGGI', 'WN_PAJU', '파주시'),    ('GYEONGGI', 'WN_GOWANG', '고양시'),    ('GYEONGGI', 'WN_KIMPO', '김포시'),    ('GYEONGGI', 'ES_YANGPYEONG', '양평군'),    ('GYEONGGI', 'ES_GWANGJU', '광주시'),    ('GYEONGGI', 'ES_SEONGNAM', '성남시'),    ('GYEONGGI', 'ES_HANAM', '하남시'),    ('GYEONGGI', 'ES_YEOJU', '여주군'),    ('GYEONGGI', 'JU_BUCHEON', '부천시'),    ('GYEONGGI', 'JU_SIHUNG', '시흥시'),    ('GYEONGGI', 'JU_GUNPO', '군포시'),    ('GYEONGGI', 'JU_ANYANG', '안양시'),    ('GYEONGGI', 'JU_GWACHEON', '과천시');

#멤버
INSERT INTO MEMBER ( MEMBER_ID, MEMBER_PW, MEMBER_NICKNAME, MEMBER_NAME, MEMBER_EMAIL, MEMBER_GENDER, MEMBER_BIRTHDAY, member_enroll_date, member_current_status, member_phone_number, TUTOR_SCHOOL_AUTHORIZE, TUTOR_MIDDLE_SCHOOL, TUTOR_HIGH_SCHOOL, TUTOR_UNI, TUTOR_UNI_IS_ENROLLED, TUTOR_MAJOR, TUTOR_MAJOR_NUM, TUTOR_AUTHORIZE)
VALUES ('admin', '(암호화해서 저장)', 'admin', '관리자', 'admin@tutorlink.com', 'F', 19900101, '2023-11-15','A', 1000000000, 'Y', '관리자', '관리자', '관리자', 'Y', '', '1', ''),
       ('jihyevivianako', '(암호화해서 저장)', 'vivi', '고지혜', 'jihyevivianako@gmail.com', 'F', 20000429, '2023-10-22','A', 1033332222, 'Y', '성내중학교', '둔촌고등학교', 'univ_code021', 'Y', '컴퓨터공학부', 20, ''),
       ('honghong22', '(암호화해서 저장)', 'hong', '홍길동', 'honggildong@gmail.com', 'M', 19920420, '2023-11-11','A' , 1033333333, 'Y', '한산중학교', '동북고등학교', 'univ_code001', 'Y', '경영학과', 13, ''),
       ('lululala1020', '(암호화해서 저장)', 'lulu', '김루루', 'rurururu@naver.com', 'F', 20021026, '2023-12-25','A', 1099999999, 'N', '영파여자중학교', '영파여자고등학교', '', 'N', '', '1', ''),
       ('hanmiyeon20', '(암호화해서 저장)', 'mimiyeon', '한미연', 'yeonhanhan@naver.com', 'F', 19990304, '2023-11-26', 'A', 1056789123, 'N', '둔둔중학교', '둔둔고등학교', '', 'N', '', '1', '');

#수업스타일
INSERT INTO class_style_category (style_code, style_name)
VALUES
    ('sty001', '열정적'),
    ('sty002', '활발함'),
    ('sty003', '꼼꼼함'),
    ('sty004', '조용함'),
    ('sty005', '섬세함');

#대학교
INSERT INTO UNIVERSITY (univ_code, univ_name, category_code)
VALUES
    ('univ_code001', '서울대학교', 'RE001'),
    ('univ_code002', '고려대학교(본캠)', 'RE001'),
    ('univ_code003', '연세대학교(본캠)', 'RE001'),
    ('univ_code004', '서강대학교', 'RE001'),
    ('univ_code005', '성균관대학교', 'RE001'),
    ('univ_code006', '한양대학교', 'RE001'),
    ('univ_code007', '중앙대학교', 'RE001'),
    ('univ_code008', '경희대학교', 'RE001'),
    ('univ_code009', '한국외국어대학교', 'RE001'),
    ('univ_code010', '서울시립대학교', 'RE001'),
    ('univ_code011', '국민대학교', 'RE001'),
    ('univ_code012', '숭실대학교', 'RE001'),
    ('univ_code013', '세종대학교', 'RE001'),
    ('univ_code014', '단국대학교', 'RE001'),
    ('univ_code015', '건국대학교', 'RE001'),
    ('univ_code016', '동국대학교', 'RE001'),
    ('univ_code017', '홍익대학교', 'RE001'),
    ('univ_code018', '숙명여자대학교', 'RE001'),
    ('univ_code019', '한성대학교', 'RE001'),
    ('univ_code020', '서경대학교', 'RE001'),
    ('univ_code021', '삼육대학교', 'RE001'),
    ('univ_code022', '서울여자대학교', 'RE001'),
    ('univ_code023', '이화여자대학교', 'RE001'),
    ('univ_code024', '카이스트(서울캠)', 'RE001'),
    ('univ_code025', '서울과학기술대학교', 'RE001'),
    ('univ_code026', '서울교육대학교', 'RE001'),
    ('univ_code027', '육군사관학교', 'RE001'),
    ('univ_code028', '한국방송통신대학교', 'RE001'),
    ('univ_code029', '한국예술종합학교(한예종)', 'RE001'),
    ('univ_code030', '한국체육대학교(한체대)', 'RE001'),
    ('univ_code031', '인천대학교', 'RE001'),
    ('univ_code032', '한경대학교', 'RE001'),
    ('univ_code033', '경인교육대학교', 'RE001'),
    ('univ_code034', '한국교통대학교', 'RE001');

INSERT INTO ACCEPTEDTYPE_CATEGORY (`my_key`, `Field`)VALUES    (1, '정시'),    (2, '수시-학종'),    (3, '수시-교과'),    (4, '적성'),    (5, '논술');

INSERT INTO authority (authority_name, authority_desc)    VALUES    ('ROLE_ADMIN','관리자'),    ('ROLE_TUTEE','튜티'),    ('ROLE_TUTOR','튜터'),    ('ROLE_BORH', '튜티&튜터');

INSERT INTO MEMBER_ROLE (authority_num,member_id)VALUES    (1,'10000000'),    (2,'10000001'),    (2,'10000002'),    (3,'10000003'),    (3,'10000004'),    (3,'10000005'),    (2,'10000006');

INSERT INTO PREFER_REGION (my_key,region_code, authority_num, member_no)VALUES    ('','GANGDONG', 2 , '10000001'),    ('','SONGPA', 2 , '10000002'),    ('','GANGNAM', 3 , '10000001'),    ('','SECHO', 2 , '10000003');

INSERT INTO SUBJECT_MAINCATEGORY (subject_code, subject_name)VALUES    ('sub001', '국어'),    ('sub002', '수학'),    ('sub003', '영어'),    ('sub004', '사회'),    ('sub005', '과학');

INSERT INTO SUBJECT_SUBCATEGORY (my_key, Field, subject_code)VALUES    ('sub001', 'subd001', '문법'),    ('sub001', 'subd002', '비문학'),    ('sub001', 'subd003', '문학'),    ('sub002', 'subd001', '수1'),    ('sub002', 'subd002', '수2'),    ('sub002', 'subd003', '확률과 통계'),    ('sub002', 'subd004', '미적분'),    ('sub002', 'subd005', '기하와 벡터');

INSERT INTO REGION (main_region_code, main_region_name)VALUES    ('1', '서울특별시'),    ('2', '부산광역시'),    ('3', '대구광역시'),    ('4', '인천광역시'),    ('5', '광주광역시'),    ('6', '대전광역시'),    ('7', '울산광역시'),    ('8', '경기도'),    ('9', '강원도'),    ('10', '충청북도'),    ('11', '충청남도'),    ('12', '전라북도'),    ('13', '전라남도'),    ('14', '경상북도'),    ('15', '경상남도'),    ('16', '제주특별자치도');

#main_region_code :varchar 15로 바꿈

INSERT INTO REGION_DETAIL (sub_region_code, main_region_code, sub_region_name)VALUES    ('INCHEON', 'GANGHWA', '강화군'),    ('INCHEON', 'SEO-GU', '서구'),    ('INCHEON', 'JUNG-GU', '중구'),    ('INCHEON', 'DONG-GU', '동구'),    ('INCHEON', 'ONGJIN', '옹진군'),    ('INCHEON', 'GWEWANG', '계양구'),    ('INCHEON', 'BUPUNG', '부평구'),    ('INCHEON', 'MICHUHOL', '미추홀구'),    ('INCHEON', 'NAMDONG', '남동구'),    ('INCHEON', 'YEONSU', '연수구'),    ('GYEONGGI', 'EN_YEONCHEON', '연천군'),    ('GYEONGGI', 'EN_POCHEON', '포천시'),    ('GYEONGGI', 'EN_DONGDUCHON', '동두천시'),    ('GYEONGGI', 'EN_YANGJU', '양주시'),    ('GYEONGGI', 'EN_', '의정부시'),    ('GYEONGGI', 'EN_NAMYANGJU', '남양주시'),    ('GYEONGGI', 'EN_GURI', '구리시'),    ('GYEONGGI', 'EN_GAPYEONG', '가평군'),    ('GYEONGGI', 'WN_PAJU', '파주시'),    ('GYEONGGI', 'WN_GOWANG', '고양시'),    ('GYEONGGI', 'WN_KIMPO', '김포시'),    ('GYEONGGI', 'ES_YANGPYEONG', '양평군'),    ('GYEONGGI', 'ES_GWANGJU', '광주시'),    ('GYEONGGI', 'ES_SEONGNAM', '성남시'),    ('GYEONGGI', 'ES_HANAM', '하남시'),    ('GYEONGGI', 'ES_YEOJU', '여주군'),    ('GYEONGGI', 'JU_BUCHEON', '부천시'),    ('GYEONGGI', 'JU_SIHUNG', '시흥시'),    ('GYEONGGI', 'JU_GUNPO', '군포시'),    ('GYEONGGI', 'JU_ANYANG', '안양시'),    ('GYEONGGI', 'JU_GWACHEON', '과천시');

#수업스타일

INSERT INTO class_style_category (style_code, style_name)
VALUES
    ('sty001', '열정적'),
    ('sty002', '활발함'),
    ('sty003', '꼼꼼함'),
    ('sty004', '조용함'),
    ('sty005', '섬세함');

INSERT INTO UNIVERSITY (univ_code, univ_name, category_code)

VALUES

    ('univ_code001', '서울대학교', 'RE001'),       ('univ_code002', '고려대학교(본캠)', 'RE001'),       ('univ_code003', '연세대학교(본캠)', 'RE001'),       ('univ_code004', '서강대학교', 'RE001'),       ('univ_code005', '성균관대학교', 'RE001'),       ('univ_code006', '한양대학교', 'RE001'),       ('univ_code007', '중앙대학교', 'RE001'),       ('univ_code008', '경희대학교', 'RE001'),       ('univ_code009', '한국외국어대학교', 'RE001'),       ('univ_code010', '서울시립대학교', 'RE001'),       ('univ_code011', '국민대학교', 'RE001'),       ('univ_code012', '숭실대학교', 'RE001'),       ('univ_code013', '세종대학교', 'RE001'),       ('univ_code014', '단국대학교', 'RE001'),       ('univ_code015', '건국대학교', 'RE001'),       ('univ_code016', '동국대학교', 'RE001'),       ('univ_code017', '홍익대학교', 'RE001'),       ('univ_code018', '숙명여자대학교', 'RE001'),       ('univ_code019', '한성대학교', 'RE001'),       ('univ_code020', '서경대학교', 'RE001'),       ('univ_code021', '삼육대학교', 'RE001'),       ('univ_code022', '서울여자대학교', 'RE001'),       ('univ_code023', '이화여자대학교', 'RE001'),       ('univ_code024', '카이스트(서울캠)', 'RE001'),       ('univ_code025', '서울과학기술대학교', 'RE001'),       ('univ_code026', '서울교육대학교', 'RE001'),       ('univ_code027', '육군사관학교', 'RE001'),       ('univ_code028', '한국방송통신대학교', 'RE001'),       ('univ_code029', '한국예술종합학교(한예종)', 'RE001'),       ('univ_code030', '한국체육대학교(한체대)', 'RE001'),       ('univ_code031', '인천대학교', 'RE001'),       ('univ_code032', '한경대학교', 'RE001'),       ('univ_code033', '경인교육대학교', 'RE001'),       ('univ_code034', '한국교통대학교', 'RE001');

#멤버

INSERT INTO MEMBER ( MEMBER_ID, MEMBER_PW, MEMBER_NICKNAME, MEMBER_NAME, MEMBER_EMAIL, MEMBER_GENDER, MEMBER_BIRTHDAY, member_enroll_date, member_current_status, member_phone_number, TUTOR_SCHOOL_AUTHORIZE, TUTOR_MIDDLE_SCHOOL, TUTOR_HIGH_SCHOOL, TUTOR_UNI, TUTOR_UNI_IS_ENROLLED, TUTOR_MAJOR, TUTOR_MAJOR_NUM, TUTOR_AUTHORIZE)VALUES ('admin', '(암호화해서 저장)', 'admin', '관리자', 'admin@tutorlink.com', 'F', 19900101, '2023-11-15','A', 1000000000, 'Y', '관리자', '관리자', '관리자', 'Y', '', '1', ''),       ('jihyevivianako', '(암호화해서 저장)', 'vivi', '고지혜', 'jihyevivianako@gmail.com', 'F', 20000429, '2023-10-22','A', 1033332222, 'Y', '성내중학교', '둔촌고등학교', 'univ_code021', 'Y', '컴퓨터공학부', 20, ''),       ('honghong22', '(암호화해서 저장)', 'hong', '홍길동', 'honggildong@gmail.com', 'M', 19920420, '2023-11-11','A' , 1033333333, 'Y', '한산중학교', '동북고등학교', 'univ_code001', 'Y', '경영학과', 13, ''),       ('lululala1020', '(암호화해서 저장)', 'lulu', '김루루', 'rurururu@naver.com', 'F', 20021026, '2023-12-25','A', 1099999999, 'N', '영파여자중학교', '영파여자고등학교', '', 'N', '', '1', ''),       ('hanmiyeon20', '(암호화해서 저장)', 'mimiyeon', '한미연', 'yeonhanhan@naver.com', 'F', 19990304, '2023-11-26', 'A', 1056789123, 'N', '둔둔중학교', '둔둔고등학교', '', 'N', '', '1', '');