package com.example.demo.event.src;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.event.dao.EventService;

@Controller
public class EventSelectController {

	// DAOのサービスクラスを宣言
	@Autowired
	EventService eventService;
	
	// 共有するイベントID
	static int shareID = 0;
	
	// GetRequestでTop画面のメイン部分に最初の文言を表示する
	@GetMapping("/eventTop")
	public String getEventTop(Model model) {
		model.addAttribute("contents", "eventTop::top_contents");
		return "/eventTopLayout";
	}
	
	// GetRequestでイベント選択画面をメイン部分に表示する
	@GetMapping("/eventSelect")
	public String getEventSelect(Model model) {
		
		// イベント共有変数の初期化
		shareID = 0;
		
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
		
		//　イベントIDを共有変数に格納
		shareID = eventID;
		
		// 指定したイベント情報を取得
		Event event = eventService.selectOne(eventID);
		
		// 指定したイベントに対応した参加者の取得
		int i = 0;
		String name = "";
		List<String> members = eventService.selectMembers(eventID);
		for (i = 0; i < members.size(); i++ ) {
			name += members.get(i);
			name += "、";
		}
		
		// モデルにデータを登録
		model.addAttribute("event", event);
		model.addAttribute("members", name);
		model.addAttribute("contents", "eventSelect::select_detail_contents");
		return "/eventTopLayout";
	}
	
	// GetRequestで参加表明画面に遷移
	@GetMapping("/eventJoin")
	public String getEventJoin(@ModelAttribute MemberForm memberForm, Model model) {
		System.out.println("----");
		model.addAttribute("contents", "eventSelect::select_join_contents");
		return "/eventTopLayout";
	}
	
	// PostRequestで参加表明画面に遷移
	@PostMapping("/eventJoin")
	public String postEventJoin(@ModelAttribute @Validated MemberForm memberForm, 
			BindingResult bindingResult, Model model) {
		
		// 入力チェックに引っかかった場合または何らかの問題で共有イベントIDが0だった時
		// GET用の処理を呼び出すことでイベント作成画面へ遷移
		if(bindingResult.hasErrors() || shareID == 0) {
			return getEventJoin(memberForm, model);
		}
		
		// DB登録用変数にデータ追加
		Member member = new Member();
		member.setMemberEventID(shareID);
		member.setMemberName(memberForm.getMemberName());
		
		// SQLで参加者テーブルにデータ追加
		boolean result = eventService.insertOneMember(member);
		
		String strResult = "";
		if(result == true) {
			strResult = "参加者登録に成功しました";
		}
		else {
			strResult = "参加者登録に失敗しました";
		}
		
		// イベント参加完了画面に遷移
		model.addAttribute("strResult", strResult);
		model.addAttribute("contents", "eventSelect::select_join_comp_contents");
		return "/eventTopLayout";
	}
	
}
