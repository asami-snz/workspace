/* ユーザーマスタ */
CREATE TABLE IF NOT EXISTS event_tbl (
	event_id INTEGER PRIMARY KEY,
	event_name VARCHAR(30),
	event_day DATE,
	event_detail VARCHAR(50)
);
