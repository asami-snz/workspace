package com.example.event.src;

import java.util.Date;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

// イベント入力用のフォームクラス
// エラーチェック用のアノテーション付与
@Data
public class EventForm {
	
	// DB側で自動付与されるのでフォーム用のクラスからは削除
	//private int    eventID;
	
	@NotBlank
	@Length(min=0, max=30)
	private String eventName;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date   eventDay;
	
	@Length(min=0, max=50)
	private String eventDetail;
}
