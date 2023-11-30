package com.capstone.teamProj_10.apiTest.user;

import com.capstone.teamProj_10.apiTest.utils.DataNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		return "signup_form";
	}

	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "signup_form";
		}

		if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스 워드가 일치 하지 않습니다.");
			return "signup_form";
		}

		try {
			userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 사용자 입니다.");
			return "signup_form";
		} catch (Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "signup_form";
		}

		return "redirect:/";
	}

	@GetMapping("/login")
	public String login() {
		return "login_form";
	}

	@PostMapping("/pwchange")
	public String changePassword(
			@RequestParam("username") String username,
			@RequestParam("currentPassword") String currentPassword,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("confirmPassword") String confirmPassword,
			Model model) {

		if (!newPassword.equals(confirmPassword)) {
			String errorMessage = "새로운 비밀번호와 비밀번호 확인이 일치하지 않습니다.";
			model.addAttribute("errorMessage", errorMessage);
			return "pwchange";
		}

		try {
			userService.changePassword(username, currentPassword, newPassword);
		} catch (DataNotFoundException e) {
			String errorMessage = "사용자를 찾을 수 없습니다.";
			model.addAttribute("errorMessage", errorMessage);
			return "pwchange";
		} catch (IllegalArgumentException e) {
			String errorMessage = "현재 비밀번호가 일치하지 않습니다.";
			model.addAttribute("errorMessage", errorMessage);
			return "pwchange";
		}

		return "redirect:/";
	}

	@GetMapping("/pwchange")
	public String showChangePasswordPage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		model.addAttribute("username", username);
		return "pwchange";
	}



}
