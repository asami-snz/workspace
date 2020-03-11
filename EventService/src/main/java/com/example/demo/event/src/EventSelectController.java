package com.example.demo.event.src;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.event.dao.EventService;

@Controller
public class EventSelectController {

	// DAOのサービスクラスを宣言
	@Autowired
	EventService eventService;
	
	// GetRequestでTop画面のメイン部分に最初の文言を表示する
	@GetMapping("/eventTop")
	public String getEventTop(Model model) {
		model.addAttribute("contents", "eventTop::top_contents");
		return "/eventTopLayout";
	}
	
	// GetRequestでイベント選択画面をメイン部分に表示する
	@GetMapping("/eventSelect")
	public String getEventSelect(Model model) {
		
		//データ件数を取得
		int count = eventService.count();
		
		//　イベント一覧の取得
		List<Event> eventList = eventService.selectMany();
		
		//Modelにデータを登録
		model.addAttribute("eventListCount", count);
		model.addAttribute("eventList", eventList);
		model.addAttribute("contents", "eventSelect::select_contents");
		
		return "/eventTopLayout";
	}
	
	// GetRequestでイベント詳細画面をメイン部分に表示する
	@GetMapping("/eventSelect/{id:.+}")
	public String getEventSelectDetail(Model model, @PathVariable("id")int eventID) {
		//　ユーザーID確認
		System.out.println("userID=" + eventID);

		// 指定したイベント情報を取得
		Event event = eventService.selectOne(eventID);
		
		// モデルにデータを登録
		model.addAttribute("event", event);
		model.addAttribute("contents", "eventSelect::select_detail_contents");
		return "/eventTopLayout";
	}
	
	// GetRequestでイベント参加完了画面をメイン部分に表示する
	@GetMapping("/eventSelectJoinComp") public String getEventSelectComp(Model model) { 
		model.addAttribute("contents", "eventSelect::select_join_comp_contents");
		return "/eventTopLayout"; 
	}
	
}
