/* イベントテーブルのデータ */
INSERT INTO event_tbl(event_name, event_day, event_detail)
VALUES('ひな祭り', '2020-03-03', 'お雛様を愛でながら、ちらし寿司を食べます');

INSERT INTO event_tbl(event_name, event_day, event_detail)
VALUES('勉強会', '2020-03-20', '新学期に向けて一年間の復習をしよう');

INSERT INTO event_tbl(event_name, event_day, event_detail)
VALUES('お花見', '2020-03-29', '空き地で桜をみながら、カラオケを楽しもう');

/* 参加者テーブルのデータ */
INSERT INTO member_tbl(member_event_id, member_name)
VALUES('1', 'ドラえもん');

INSERT INTO member_tbl(member_event_id, member_name)
VALUES('1', '野比のび太');

INSERT INTO member_tbl(member_event_id, member_name)
VALUES('1', '源しずか');

INSERT INTO member_tbl(member_event_id, member_name)
VALUES('2', '出木杉英才');

INSERT INTO member_tbl(member_event_id, member_name)
VALUES('2', '骨川スネ夫');

INSERT INTO member_tbl(member_event_id, member_name)
VALUES('3', '剛田武');

INSERT INTO member_tbl(member_event_id, member_name)
VALUES('3', 'ドラえもん');

INSERT INTO member_tbl(member_event_id, member_name)
VALUES('3', '骨川スネ夫');