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

INSERT INTO COMMENT(comment_content, comment_regist_date, commnet_is_deleted, comment_update_date, comment_delete_date, post_num, member_no)
VALUES ('감사합니다', '2023-10-31 14:22:21', 'N', NULL, NULL, '2db2c1b8-7e4b-11ee-8272-4bc2e949b19f', 10000002),
       ('네~ 열공하세요', '2023-10-31 14:28:21', 'N', NULL, NULL, '2db2c1b8-7e4b-11ee-8272-4bc2e949b19f', 10000001),
       ('네~ 열공하세요', '2023-11-01 16:28:21', 'N', NULL, NULL, '2db2c1b8-7e4b-11ee-8272-4bc2e949b19f', 10000001),
       ('네~ 열공하세요', '2023-11-01 20:58:21', 'Y', NULL, '2023-11-01 21:05:36', '2db2c1b8-7e4b-11ee-8272-4bc2e949b19f', 10000001);
select * from COMMENT;