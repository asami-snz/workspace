package com.example.demo.event.src;

import java.util.Date;
import lombok.Data;

// DBより取得したイベント詳細を格納するクラス
@Data
public class Event {
	
	private int    eventID;
	private String eventName;
	private Date   eventDay;
	private String eventDetail;
}
