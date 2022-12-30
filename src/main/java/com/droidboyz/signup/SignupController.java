package com.droidboyz.signup;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.droidboyz.db.User;

@Controller
@SessionAttributes(names = { "name", "uid" })
public class SignupController {

	@Autowired
	SignupService service;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String showSignupPage(ModelMap map) {
		map.addAttribute("user",new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUpAndShowWelcome(ModelMap model,@Valid User user, BindingResult result ) {
		String msg="";
		if (!result.hasErrors()) {
			msg = service.createUser(user);
			if (msg.isEmpty()) {
				model.addAttribute("uid", user.getUid());
				model.addAttribute("name", user.getName());
				return "welcome";
			}
		}
		model.addAttribute("err", msg);
		return "signup";
	}
}
