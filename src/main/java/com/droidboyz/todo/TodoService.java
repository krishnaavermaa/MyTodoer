package com.droidboyz.todo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.droidboyz.db.FakeDB;
import com.droidboyz.db.User;

@Service
public class TodoService {

	@Autowired
	FakeDB db;

	public List<Todo> getTodosByUid(Integer uid) {
		List<Todo> list = null;
		if (uid != null && uid != 0)
			list = db.retrieveAll(uid);
		return list;

	}

	public Todo getTodoByTid(Integer uid, Integer tid) {
		Todo todo = null;
		if (uid != null && tid != null && uid != 0 && tid != 0)
			todo = db.retrieveTodo(uid, tid);
		return todo;

	}

	public boolean deleteTodo(Integer uid, Integer tid) {
		boolean isDeleted = false;
		if (uid != null && uid != 0 && tid != null && tid != 0 && db.deleteTodo(uid, tid))
			isDeleted = true;
		return isDeleted;
	}

	public boolean createTodo(Integer uid, Todo todo) {

		boolean isInserted = false;
		if (uid != null && uid != 0 && todo != null && db.insertTodo(uid, todo))
			isInserted = true;
		return isInserted;
	}

	public boolean updateTodo(Integer uid, Todo temp) {
		Todo todo = db.retrieveTodo(uid, temp.getId());
		if (todo != null && temp != null) {
			todo.setTarget(temp.getTarget());
			todo.setDesc(temp.getDesc());
			todo.setDone(temp.isDone());
			return true;
		}
		return false;
	}

	public Todo makeCopy(Todo todo) {
		// TODO Auto-generated method stub
		Todo temp = new Todo();
		temp.setId(todo.getId());
		temp.setDesc(todo.getDesc());
		temp.setTarget(todo.getTarget());
		temp.setDone(todo.isDone());
		return temp;
	}

}
