package com.fastcampus.web.controller;

import com.fastcampus.auth.PrincipalDetails;
import com.fastcampus.biz.domain.User;
import com.fastcampus.biz.service.BlogService;
import com.fastcampus.biz.service.UserService;
import com.fastcampus.web.controller.dto.BlogListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class WelcomeController {
	private final BlogService blogService;
	
	@GetMapping({"", "/"})
	public String welcome(Model model,
						  @RequestParam(required = false, defaultValue = "") String searchCondition,
						  @RequestParam(required = false, defaultValue = "") String searchKeyword) {

		List<BlogListResponseDto> list = blogService.getBlogList(searchCondition,searchKeyword);
		model.addAttribute("blogList", list);

		return "welcome";
	}

	@GetMapping("/auth/loginForm")
	public String loginForm(){
		return "/system/login";
	}
}
