package com.example.event.dao;

import java.util.*;
import org.springframework.stereotype.Service;

import com.example.event.src.*;

import org.springframework.beans.factory.annotation.*;

/* EventDAOのラッパークラス（service） */

@Service
public class EventService {
	@Autowired
	EventDAO eDao;
	
	//カウント用メソッド
	public int count() 
	{
		return eDao.count();
	}
	
	// データ追加用メソッド
	public boolean insert(Event event) 
	{
		
		boolean result = false;
		//insert実行
		int rowNumber = eDao.insertOne(event);
		if(rowNumber > 0)
		{
			//取得成功
			result = true;
		}
		return result;
	}
	
	//データ1件取得用メソッド
	public Event selectOne(int eventID) 
	{
		//select実行
		return eDao.selectOne(eventID);
	}
	
	//データ全件取得用メソッド
	public List<Event> selectMany() 
	{
		return eDao.selectMany();
	}
	
	//1件更新メソッド
	public boolean updateOne(Event event)
	{
		boolean result = false;
		//1件更新
		int rowNumber = eDao.updateOne(event);
		if( rowNumber > 0 ) 
		{
			//取得成功
			result = true;
		}
		return result;
	}
	
	//1件削除メソッド
	public boolean deleteOne(int eventID) 
	{
		boolean result = false;
		//1件削除
		int rowNumber = eDao.deleteOne(eventID);		
		if(rowNumber > 0) 
		{
			//delete成功
			result = true;
		}
		return result;
	}
	
}
