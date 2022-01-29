select * from member;

select * from board 
select * from reply;

delete from reply where num=69;

alter table member modify zip_num varchar2(20) null;
alter table member modify address varchar2(100) null;
alter table member modify phone varchar2(20) null;


    insert into reply(boardnum, userid, content, writedate, num)
    values(194, 'scott', '추가', sysdate, reply_seq.nextval);
    
    insert into reply(boardnum, userid, content, num, writedate)
    values(194, 'scott', '추가', reply_seq.nextval, sysdate);
    commit;