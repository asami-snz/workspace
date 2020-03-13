package com.example.demo.event.src;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class MemberForm {
	
	@NotBlank
	@Length(min=0, max=30)
	private String memberName;
}
