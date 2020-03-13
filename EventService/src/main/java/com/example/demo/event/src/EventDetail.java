package com.example.demo.event.src;

import java.util.Date;
import lombok.Data;

// 本当はEventクラスを継承したいところだけど
// lombokで変数をprivateで宣言してるので無理ぽ
@Data
public class EventDetail {
	
	private int    eventID;
	private String eventName;
	private Date   eventDay;
	private String eventDetail;
	private String eventMemberName;    // 参加者名
}
