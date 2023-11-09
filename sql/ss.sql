INSERT INTO ACCEPTEDTYPE_CATEGORY (`Key`, `Field`)
VALUES (1, '수시-논술'),

       (1, '수시-학생부종합전형'),

       (2, '수시-교과'),

       (3, '수시-적성'),

       (4, '수능-일반'),

       (5, '수능-실기');

INSERT INTO `AUTHORITY` (`authority_num`,`authority_name`)
VALUES
    (1,'관리자'),
    (2,'튜티'),
    (3,'튜터');

INSERT INTO MEMBER_ROLE (authority_num,member_no)
VALUES
    (1,'10000000'),
    (2,'10000001'),

    (2,'10000002'),

    (3,'10000003'),

    (3,'10000004'),

    (3,'10000005'),

    (2,'10000006');

INSERT INTO PREFER_REGION (region_code, authority_num, member_no)
VALUES
    ('GANGDONG', 2 , '10000001'),
    ('SONGPA', 2 , '10000002'),
    ('GANGNAM', 3 , '10000001'),
    ('SECHO', 2 , '10000003');

INSERT INTO SUBJECT_MAINCATEGORY (subject_code, subject_name)
VALUES
    ('sub001', '국어'),
    ('sub002', '수학'),
    ('sub003', '영어'),
    ('sub004', '사회'),
    ('sub005', '과학');

INSERT INTO SUBJECT_SUBCATEGORY (sub_category_key, sub_category_field, subject_code)
VALUES
    ('sub001', 'subd001', '문법'),
    ('sub001', 'subd002', '비문학'),
    ('sub001', 'subd003', '문학'),
    ('sub002', 'subd001', '수1'),
    ('sub002', 'subd002', '수2'),
    ('sub002', 'subd003', '확률과 통계'),
    ('sub002', 'subd004', '미적분'),
    ('sub002', 'subd005', '기하와 벡터');

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

INSERT INTO REGION_DETAIL (sub_region_code, main_region_code, sub_region_name)
VALUES
    ('INCHEON', 'GANGHWA', '강화군'),
    ('INCHEON', 'SEO-GU', '서구'),
    ('INCHEON', 'JUNG-GU', '중구'),
    ('INCHEON', 'DONG-GU', '동구'),
    ('INCHEON', 'ONGJIN', '옹진군'),
    ('INCHEON', 'GWEWANG', '계양구'),
    ('INCHEON', 'BUPUNG', '부평구'),
    ('INCHEON', 'MICHUHOL', '미추홀구'),
    ('INCHEON', 'NAMDONG', '남동구'),
    ('INCHEON', 'YEONSU', '연수구'),
    ('GYEONGGI', 'EN_YEONCHEON', '연천군'),
    ('GYEONGGI', 'EN_POCHEON', '포천시'),
    ('GYEONGGI', 'EN_DONGDUCHON', '동두천시'),
    ('GYEONGGI', 'EN_YANGJU', '양주시'),
    ('GYEONGGI', 'EN_', '의정부시'),
    ('GYEONGGI', 'EN_NAMYANGJU', '남양주시'),
    ('GYEONGGI', 'EN_GURI', '구리시'),
    ('GYEONGGI', 'EN_GAPYEONG', '가평군'),
    ('GYEONGGI', 'WN_PAJU', '파주시'),
    ('GYEONGGI', 'WN_GOWANG', '고양시'),
    ('GYEONGGI', 'WN_KIMPO', '김포시'),
    ('GYEONGGI', 'ES_YANGPYEONG', '양평군'),
    ('GYEONGGI', 'ES_GWANGJU', '광주시'),
    ('GYEONGGI', 'ES_SEONGNAM', '성남시'),
    ('GYEONGGI', 'ES_HANAM', '하남시'),
    ('GYEONGGI', 'ES_YEOJU', '여주군'),
    ('GYEONGGI', 'JU_BUCHEON', '부천시'),
    ('GYEONGGI', 'JU_SIHUNG', '시흥시'),
    ('GYEONGGI', 'JU_GUNPO', '군포시'),
    ('GYEONGGI', 'JU_ANYANG', '안양시'),
    ('GYEONGGI', 'JU_GWACHEON', '과천시');

INSERT INTO MEMBER (MEMBER_NO, MEMBER_ID, MEMBER_PW, MEMBER_NICKNAME, MEMBER_NAME, MEMBER_EMAIL, MEMBER_GENDER, MEMBER_BIRTHDAY, ENROLL_DATE, MEMBER_STATUS, MEMBER_PHONENUMBER, TUTOR_SCHOOL_AUTHORIZE, TUTOR_MIDDLE_SCHOOL, TUTOR_HIGH_SCHOOL, TUTOR_UNI, TUTOR_UNI_IS_ENROLLED, TUTOR_MAJOR, TUTOR_MAJOR_NUM, TUTOR_AUTHORIZE, `KEY`, MEMBER_CURRENT_STATUS, MEMBER_ENROLL_DATE, MEMBER_PHONE_NUMBER, MY_KEY)
VALUES (10000000, 'admin', '(암호화해서 저장)', 'admin', '관리자', 'admin@tutorlink.com', 'F', 19900101, 'A', 1000000000, 'T', '관리자', '관리자', '', '', '', '', '', '', 1),
       (10000001, 'jihyevivianako', '(암호화해서 저장)', 'vivi', '고지혜', 'jihyevivianako@gmail.com', 'F', 20000429, 'A', 1033332222, 'T', '성내중학교', '둔촌고등학교', 'univ_code021', 'T', '컴퓨터공학부', 20, '', '', 'pss_code01', 2),
       (10000002, 'honghong22', '(암호화해서 저장)', 'hong', '홍길동', 'honggildong@gmail.com', 'M', 19920420, 'A', 1033333333, 'T', '한산중학교', '동북고등학교', 'univ_code001', 'F', '경영학과', 13, '', '', 'pss_code01', 2),
       (10000003, 'lululala1020', '(암호화해서 저장)', 'lulu', '김루루', 'rurururu@naver.com', 'F', 20021026, 'A', 1099999999, 'F', '영파여자중학교', '영파여자고등학교', '', '', '', '', '', '', 3),
       (10000004, 'hanmiyeon20', '(암호화해서 저장)', 'mimiyeon', '한미연', 'yeonhanhan@naver.com', 'F', 19990304, 'A', 1056789123, 'F', '둔둔중학교', '둔둔고등학교', '', '', '', '', '', '', 3);

INSERT INTO class_style_category (style_code, style_name)
VALUES
    ('sty001', '열정적'),
    ('sty002', '활발함'),
    ('sty003', '꼼꼼함'),
    ('sty004', '조용함'),
    ('sty005', '섬세함');

INSERT INTO class_information (member_no, subject_code, info_cost, info_content, style_code)
VALUES
    ('10000001', 'sub004', '20000', '[8주 완성] 수1 개념 빠르게 1회독진행해드립니다.1주일에 한번씩 단원평가를 진행합니다.', 'sty001'),
    ('10000001', 'sub007', '25000', '[수능 전문] 미적분 과목 모의고사 과외합니다.', 'sty002');

CREATE TABLE UNIVERSITY (
                     univ_code VARCHAR(20),
                     univ_name VARCHAR(50),
                     category_code VARCHAR(10)
);

INSERT INTO UNIVERSITY (univ_code, univ_name, category_code)
VALUES ('univ_code001', '서울대학교', 'RE001'),
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
