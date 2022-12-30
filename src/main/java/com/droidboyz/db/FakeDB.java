package com.droidboyz.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.droidboyz.todo.Todo;
import com.droidboyz.db.User;

@Component
public class FakeDB {

	private int todoCount;
	private Map<Integer,Todo> todo_db;
	private Map<Integer,List<Integer>> uid_tid_db;
	private Map<String,User> user_db;
	
	FakeDB(){
		todoCount=0;
		todo_db=new HashMap<>();
		uid_tid_db=new HashMap<>();
		user_db=new HashMap<>();
	}
	
	public boolean createUser(User user)
	{
		if(!user_db.containsKey(user.getEmail()))
		{
			user_db.put(user.getEmail(),user);
			List<Integer> emptyList=new ArrayList<>();
			uid_tid_db.put(user.getUid(), emptyList);
			System.out.println("users in db="+user_db.size()+" todos count for curr user="+todo_db.size());
			return true;
		}
		return false;
	}
	public boolean insertTodo(int uid,Todo todo)
	{
		if(uid_tid_db.containsKey(uid))
		{
			List<Integer> tidList=(ArrayList<Integer>)uid_tid_db.get(uid);
			todo.setId(++todoCount);
			tidList.add(todoCount);
			uid_tid_db.put(uid, tidList);
			todo_db.put(todoCount,todo);
			return true;
		}
		return false;
	}
	public List<Todo> retrieveAll(int uid)
	{
		List<Integer> list=uid_tid_db.get(uid);
		if(list==null) return null;
		List<Todo> res=new ArrayList<>();
		for(int tid:list)
		{
			res.add(todo_db.get(tid));
		}
		return res;
	}
	public Todo retrieveTodo(int uid, int tid)
	{
		List<Integer> list=uid_tid_db.get(uid);
		if(list==null || !list.contains(tid)) return null;
		return todo_db.get(tid);
	}
	public boolean deleteTodo(int uid, int tid)
	{
		if(uid_tid_db.containsKey(uid))
		{
			List<Integer> tidList=(ArrayList<Integer>)uid_tid_db.get(uid);
			if(tidList.contains(tid)) {
				todo_db.remove(tid);
				tidList.remove(Integer.valueOf(tid));
				uid_tid_db.put(uid, tidList);
				return true;
			}
		}
		return false;
	}
	public User getUser(String email, String pass)
	{
		User cur=user_db.get(email);
		if(cur==null || !cur.getPassword().equals(pass)) return null;
		return cur;
	}

	public boolean checkExisting(String email) {
		if(user_db.containsKey(email)) return true;
		return false;
	}
	
	
	
	
}
