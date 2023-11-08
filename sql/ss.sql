INSERT INTO ACCEPTEDTYPE_CATEGORY (`Key`, `Field`)
VALUES (1, '수시-논술'),

       (pss_type002, '수시-학생부종합전형'),

       (pss_type003, '수시-교과'),

       (pss_type004, '수시-적성'),

       (pss_type005, '수능-일반'),

       (pss_type006, '수능-실기');

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

INSERT INTO prefer_region (region_code, authority_num, member_no)
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

INSERT INTO subject_subcategory (sub_category_key, sub_category_field, subject_code)
VALUES
    ('sub001', 'subd001', '문법'),
    ('sub001', 'subd002', '비문학'),
    ('sub001', 'subd003', '문학'),
    ('sub002', 'subd001', '수1'),
    ('sub002', 'subd002', '수2'),
    ('sub002', 'subd003', '확률과 통계'),
    ('sub002', 'subd004', '미적분'),
    ('sub002', 'subd005', '기하와 벡터');