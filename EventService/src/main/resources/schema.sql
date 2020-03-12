/* イベントテーブルマスタ */
CREATE TABLE IF NOT EXISTS event_tbl (
	event_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	event_name VARCHAR(30) NOT NULL,
	event_day DATE NOT NULL,
	event_detail VARCHAR(50)
);

/* 参加者テーブルマスタ */
/* 参加イベントについては、イベントテーブルのIDを参照する */
CREATE TABLE IF NOT EXISTS member_tbl (
	member_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	member_event_id INTEGER NOT NULL,
	member_name VARCHAR(20) NOT NULL,
	FOREIGN KEY(member_event_id) REFERENCES event_tbl(event_id)
);