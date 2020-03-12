package com.example.demo.event.src;

import lombok.Data;

//DBとのやりとりで使用するクラス
@Data
public class Member {

	private int    memberID;      //　参加者ID（自動インクリメント）
	private int    memberEventID; // 参加するイベントID
	private String memberName;    // 参加者名
}
