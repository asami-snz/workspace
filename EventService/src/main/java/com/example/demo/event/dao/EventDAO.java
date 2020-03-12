package com.example.demo.event.dao;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.event.src.*;

@Repository("EventDAO")
public class EventDAO {
	@Autowired
	JdbcTemplate jdbc;
	
	// Eventテーブルの件数を取得
	public int count() throws DataAccessException {
		//全件取得してカウント
		int count = jdbc.queryForObject("SELECT COUNT(*) FROM event_tbl", Integer.class);
		return count;
	}
	
	// Eventテーブルにデータを一件Insert
	public int insertOne(Event event) throws DataAccessException {
		
		//1件登録
		int rowNumber = jdbc.update("INSERT INTO event_tbl("
				+ " event_name,"
				+ " event_day,"
				+ " event_detail)"
				+ " VALUES(?,?,?)"
				, event.getEventName()
				, event.getEventDay()
				, event.getEventDetail());		
		return rowNumber;
	}
	
	// Eventテーブルからデータを一件取得
	public Event selectOne(int eventID) throws DataAccessException {
		Map<String, Object>map = jdbc.queryForMap("SELECT * FROM event_tbl"
				+ " WHERE event_id=?"
				, eventID);
		//結果返却用の変数
		Event event = new Event();
		
		//取得したデータを結果返却用の変数にセットしていく
		event.setEventID(eventID);
		event.setEventName((String)map.get("event_name"));
		event.setEventDay((Date)map.get("event_day"));
		event.setEventDetail((String)map.get("event_detail"));
		
		return event;
	}
	
	// Eventテーブルの全データを取得
	public List<Event> selectMany() throws DataAccessException {
		//結果返却用の変数
		List<Event> eventList = new ArrayList<>();
			
		// event_tblテーブルのデータを全件取得
		List<Map<String,Object>> getList = jdbc.queryForList("SELECT * FROM event_tbl");
			
		//取得したデータを返却用のListに格納
		for(Map<String, Object> map:getList) {
			//Eventインスタンスの生成
			Event event = new Event();
				
			//取得したデータをインスタンスにセットしていく
			event.setEventID((Integer)map.get("event_id"));
			event.setEventName((String)map.get("event_name"));
			event.setEventDay((Date)map.get("event_day"));
			event.setEventDetail((String)map.get("event_detail"));
				
			//返却用リストに格納
			eventList.add(event);
		}
		return eventList;
	}
	
	// Eventテーブルの情報更新
	public int updateOne(Event event) throws DataAccessException {
		
		int rowNumber = jdbc.update("UPDATE event_tbl SET"
				+ " event_name = ?,"
				+ " event_day = ?,"
				+ " event_detail = ?"
				, event.getEventName()
				, event.getEventDay()
				, event.getEventDetail());
		
		return rowNumber;
	}
	
	// Eventテーブルの情報を1件削除
	public int deleteOne(int eventID) throws DataAccessException {
		int rowNumber = jdbc.update("DELETE FROM event_tbl WHERE event_id = ?", eventID);
		return rowNumber;
	}
	
	// 指定したイベントIDに紐づいている参加者を取得
	public List<String> selectMembers(int eventID) throws DataAccessException {
		// 結果返却用の変数
		List<String> member_name = new ArrayList<>();
		
		// イベントテーブル、参加者テーブルを結合した結果を取得
		List<Map<String,Object>> getList = jdbc.queryForList(""
				+ "SELECT m.member_name FROM member_tbl AS m"
				+ " INNER JOIN event_tbl AS e"
				+ " ON m.member_event_id=e.event_id"
				+ " AND e.event_id=?"
				, eventID );
		
		//取得したデータを返却用Listに格納
		for(Map<String, Object> map:getList) {
			String str = (String)map.get("member_name");
			member_name.add(str);
		}
		
		return member_name;
	}
	
	// イベント参加者テーブルにメンバーデータを1件追加する
	// Eventテーブルにデータを一件Insert
	public int insertOneMember(Member member) throws DataAccessException {
		
		//1件登録
		int rowNumber = jdbc.update("INSERT INTO member_tbl("
				+ " member_event_id,"
				+ " member_name)"
				+ " VALUES(?,?)"
				, member.getMemberEventID()
				, member.getMemberName());		
		return rowNumber;
	}
	
	
}

