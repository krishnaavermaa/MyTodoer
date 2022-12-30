package com.droidboyz.login;

import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import com.droidboyz.db.User;
import com.droidboyz.todo.TodoService;

@Controller
@SessionAttributes(names={"name","uid"})
public class LoginController {

	@Autowired
	LoginService service;
	@Autowired
	LoginValidationService validation;
	@Autowired
	TodoService todoService;

	
	@RequestMapping(value="/")
	public String showHomepage()
	{
		return "redirect:login";
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.GET )
	public String showLoginPage(ModelMap model) {
		if(model.containsAttribute("uid")) {
			model.clear();
			return "redirect:welcome";
		}
		return "login";
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String logInAndWelcome(@RequestParam String email, @RequestParam String pass, ModelMap model)
	{
		email=email.trim();
		String msg=validation.validate(email,pass);
		if(msg=="")
		{
			User user=service.validateUser(email,pass);
			if(user!=null)
			{
				model.addAttribute("name", user.getName());
				model.addAttribute("uid", user.getUid());
				return "redirect:login";
			}
			msg="Invalid email id or Password!";

		}
		model.addAttribute("err", msg);
		return "login";

	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(SessionStatus status)
	{
		status.setComplete();
		return "redirect:/";
	}
}
