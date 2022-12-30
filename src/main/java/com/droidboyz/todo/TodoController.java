package com.droidboyz.todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(names = { "name", "uid" })
public class TodoController {

	@Autowired
	TodoService service;

	@InitBinder("todo")
	public void binder(WebDataBinder binder) {
		System.out.println(binder.getObjectName());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String getWelcomePage(ModelMap model, @RequestParam(required = false) String err) {
		if (model.get("uid") != null) 
		{
			int uid = (Integer) model.get("uid");
			model.addAttribute("listTodos", service.getTodosByUid(uid));
			if (err != null)
				model.addAttribute("err", "Database error, Unable to delete todo.");
			return "welcome";
		}
		return "login";
	}

	@RequestMapping(value = "/todo", method = RequestMethod.GET)
	public String getTodoPage(ModelMap model, @RequestParam(required = false) Integer tid) {
		if (model.get("uid") != null) 
		{
			Todo temp = new Todo();
			if (tid != null) 
			{
				Todo todo = service.getTodoByTid((Integer) model.get("uid"), tid);
				if (todo != null) 
				{
					temp=service.makeCopy(todo);
				}
				else
				{
					model.addAttribute("err", "No ToDo with the given id exists. Creating new ToDO.");
				}
			}
			model.clear();
			model.addAttribute("todo", temp);
			return "todo";
		}
		return "login";
	}

	@RequestMapping(value = "/todo", method = RequestMethod.POST)
	public String setTodoPage(ModelMap model, @Valid Todo todo, BindingResult result, @RequestParam(required = false) String status) 
	{
		System.out.println(status);
		if(status!=null && status.equals("on")) todo.setDone(true);
		System.out.println(todo);
		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"); Date date;
		 * try { System.out.println("**********"+target); date = sdf.parse(target);
		 * System.out.println("**********"+date); } catch (ParseException e) { // TODO
		 * Auto-generated catch block model.addAttribute("err",
		 * "Invalid date-time format."); return "todo"; }
		 */
		Integer uid = model.get("uid") != null ? (Integer) model.get("uid") : null;
		if (uid != null) 
		{
			if (!result.hasErrors()) 
			{
				if (todo.getId() == 0) 
				{
					System.out.println("*******Creating new todo");
					if (!service.createTodo(uid, todo)) 
					{
						model.addAttribute("err", "Database error, Unable to insert todo.");
						return "todo";
					}
				}
//				  else if(!service.updateTodo(uid, Integer.valueOf(tid), date, desc,
//				  isDone!=null?isDone:false)) {
				else if (!service.updateTodo(uid, todo)) 
				{
					model.addAttribute("err", "Database error, Unable to update todo");
					return "todo";
				}
				model.clear();
				return "redirect:/welcome";
			}
			return "todo";
		}
		model.clear();
		return "redirect:login";
	}

	@RequestMapping(value = "/delete")
	public String deleteTodo(ModelMap model, @RequestParam(required = false) Integer tid) {
		int uid = (Integer) model.get("uid");
		model.clear();
		if (tid != null && tid > 0) 
		{
			if (service.deleteTodo(uid, tid)) 
			{
				System.out.println("deleted");
				return "redirect:/welcome";
			}
		}
		return "redirect:/welcome?err=2";

	}

}
