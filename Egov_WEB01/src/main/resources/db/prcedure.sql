create or replace PROCEDURE selectBoard (
    p_rc OUT SYS_REFCURSOR )
IS
BEGIN
    OPEN p_rc FOR
        SELECT * FROM BOARD ORDER BY NUM DESC;
END;
DECLARE
    p_rec SYS_REFCURSOR;
BEGIN
    selectBoard(p_rec);
    
END;

create or replace PROCEDURE selectBoard (
    p_startNum IN NUMBER,
    p_endNum IN NUMBER,
    p_rc OUT SYS_REFCURSOR )
IS
BEGIN
    OPEN p_rc FOR
        select * from ( select * from (
		select rownum as rn, b.* from ((select * from board order by num desc) b)
		) where rn >= p_startNum ) where rn <= p_endNum;
END;

create or replace PROCEDURE selectBoard (
    p_startNum IN NUMBER,
    p_endNum IN NUMBER,
    p_rc OUT SYS_REFCURSOR )
IS
    temp_rc SYS_REFCURSOR; -- 중간 조회결과를 담을 커서
    vs_cnt NUMBER; -- 게시물번호로 조회한 해당 게시물의 댓글개수를 저장할 변수
    vs_num NUMBER; -- 중간조회결과 중 게시물번호를 저장할 변수
    vs_rownum NUMBER; -- 중간조회결과 중 행번호를 저장할 변수
BEGIN
 -- startNum과 endNum 사이의 게시물을 게시물번호만 조회해서 커서에 담고,
 -- 게시물번호로 댓글개수를 조회해서 다시 그 개수를 게시물번호에 해당하는 레코드에 업데이트
 -- 다시 startNum과 endNum 사이의 게시물을 모든 필드를 대상으로 조회하여 p_rc에 전달
    OPEN temp_rc FOR
        select * from ( select * from (
		select rownum as rn, b.num from ((select * from board order by num desc) b)
		) where rn >= p_startNum ) where rn <= p_endNum; -- 커서에는 해달 게시물의 게시물번호와 행번호가 하나의 레코드로 저장됩니다
    LOOP
        FETCH temp_rc into vs_num, vs_rownum; -- 레코드들(게시물 번호, 행번호)이 저장된 커서에서 한 행씩 꺼내서 처리
        EXIT WHEN temp_rc%NOTFOUND;
        select count(*) into vs_cnt from reply where boardnum=vs_num; -- 꺼낸 게시물번호로 댓글개수 조회
        update board set replycnt=vs_cnt where num=vs_num; -- 조회된 댓글개수는 replycnt필드에 업데이트
        commit;
    END LOOP;
    OPEN p_rc FOR
        select * from ( select * from (
		select rownum as rn, b.* from ((select * from board order by num desc) b)
		) where rn >= p_startNum ) where rn <= p_endNum;
END;








create or replace PROCEDURE selectBoardOne (
    p_num IN board.num%type,
    p_rc OUT SYS_REFCURSOR )
IS
BEGIN   
    UPDATE BOARD SET READCOUNT = READCOUNT +1 WHERE NUM = p_num; -- 조회수 1 증가
    COMMIT;
    
    OPEN p_rc FOR
        SELECT * FROM BOARD WHERE NUM=p_num ORDER BY NUM DESC; -- 게시물 번호로 조회 후 커서에 저장
END;



create or replace PROCEDURE selectBoardOneWithoutCount (
    p_num IN board.num%type,
    p_rc OUT SYS_REFCURSOR )
IS
BEGIN  
    OPEN p_rc FOR
        SELECT * FROM BOARD WHERE NUM=p_num ORDER BY NUM DESC; -- 게시물 번호로 조회 후 커서에 저장
END;



create or replace PROCEDURE selectMember (
    p_id IN member.id%type,
    p_rc OUT SYS_REFCURSOR )
IS
BEGIN
    OPEN p_rc FOR
        SELECT * FROM MEMBER WHERE ID=p_id; 
END;



create or replace PROCEDURE insertMember (
    p_id IN member.id%type,
    p_pwd IN member.pwd%type,
    p_name IN member.name%type,
    p_email IN member.email%type,
    p_phone IN member.phone%type
)
IS
BEGIN
    INSERT into member (id, pwd, name, email, phone)
    values(p_id, p_pwd, p_name, p_email, p_phone);
    commit;
END;




create or replace PROCEDURE updateMember (
    p_id IN member.id%type,
    p_pwd IN member.pwd%type,
    p_name IN member.name%type,
    p_email IN member.email%type,
    p_phone IN member.phone%type
)
IS
BEGIN
    update member set name=p_name, pwd=p_pwd, email=p_email, 
		phone=p_phone where id=p_id;
    commit;
END;




create or replace PROCEDURE insertBoard (
    p_userid IN board.userid%type,
    p_pass IN board.pass%type,
    p_email IN board.email%type,
    p_title IN board.title%type,
    p_content IN board.content%type,
    p_imgfilename IN board.content%type
)
IS
BEGIN
    insert into board(num, userid, pass, email, title, content, imgfilename)
    values(board_seq.nextval, p_userid, p_pass, p_email, p_title, p_content, p_imgfilename);
    commit;
END;




create or replace PROCEDURE updateBoard (
    p_num IN board.num%type,
    p_pass IN board.pass%type,
    p_email IN board.email%type,
    p_title IN board.title%type,
    p_content IN board.content%type,
    p_imgfilename IN board.content%type
)
IS
BEGIN
    update board set pass=p_pass, email=p_email, title=p_title, content=p_content, imgfilename=p_imgfilename
        where num=p_num; 
    commit;
END;




create or replace PROCEDURE getAllCount (
    p_count OUT NUMBER ) -- 매개변수
IS
    vs_count NUMBER; -- 프로시저 내에 선언한 지역변수
BEGIN
    SELECT count(*) into vs_count FROM board;
     p_count := vs_count;
END;


create or replace PROCEDURE boardDelete (
    p_num IN board.num%type
)
IS
BEGIN
    delete from board where num=p_num;
    commit;
END;





create or replace PROCEDURE selectReply (
    p_num IN reply.num%type,
    p_rc OUT SYS_REFCURSOR )
IS
BEGIN   
    OPEN p_rc FOR
        select * from reply where boardnum = p_num order by num desc;
END;




create or replace PROCEDURE addReply (
    p_boardnum IN reply.boardnum%type,
    p_userid IN reply.userid%type,
    p_content IN reply.content%type
)
IS
BEGIN
    insert into reply(boardnum, userid, content, num, writedate)
    values(p_boardnum, p_userid, p_content, reply_seq.nextval, sysdate);
    commit;
END;



create or replace PROCEDURE deleteReply (
    p_num IN reply.num%type
)
IS
BEGIN
    delete from reply where num=p_num;
    commit;
END;