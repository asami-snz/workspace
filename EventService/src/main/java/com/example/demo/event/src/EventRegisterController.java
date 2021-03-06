package com.example.demo.event.src;

//import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.event.dao.EventService;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@Controller
public class EventRegisterController {

	 // DAOのサービスクラスを宣言 
	 @Autowired 
	 EventService eventService;
	
	// GetRequestでイベント作成画面をメイン部分に表示する
	// @ModelAttributeでEventForm型の変数を渡す
	@GetMapping("/eventRegister")
	public String getEventRegister(@ModelAttribute EventForm eventForm, Model model) {
		model.addAttribute("contents", "eventRegister::regist_contents");
		return "/eventTopLayout";
	}
	
	
	// PostRequestでイベント情報を取得する
	// BindingResult、@Validatedでエラーを受け取る
	@PostMapping("/eventRegister")
	public String postNewEventRegister(@ModelAttribute @Validated EventForm eventForm, 
			BindingResult bindingResult, Model model) {
		
		// insert用変数
		Event event = new Event();
		
		// 入力チェックに引っかかった場合
		// GET用の処理を呼び出すことでイベント作成画面へ遷移
		if(bindingResult.hasErrors()) {
			return getEventRegister(eventForm, model);
		}
		
		// eventクラスへ格納
		event.setEventName(eventForm.getEventName());
		event.setEventDay(eventForm.getEventDay());
		event.setEventDetail(eventForm.getEventDetail());;
		
		// 取得した内容をコンソールへ出力
		System.out.println(event);
		
		// insert処理呼び出し
		boolean result = eventService.insert(event);
		if(result == true) {
			model.addAttribute("contents", "eventRegister::regist_comp_contents");
		}
		else {
			model.addAttribute("contents", "eventRegister::regist_failure_contents");
		}
		
		// 登録完了画面に遷移
		return "/eventTopLayout";
	}

}
