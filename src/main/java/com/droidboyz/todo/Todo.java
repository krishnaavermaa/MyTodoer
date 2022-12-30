package com.droidboyz.todo;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;



public class Todo {
	
	private int id;
	@Future(message = "Select future date-time.")
	private Date target;
	@Length(min = 1, max= 150, message="Description should be 1-150 chars")
	private String desc;
	private boolean isDone;
	
	public Todo() {
	}
	public Todo(Date target, String desc, boolean isDone) {
		super();
		this.target = target;
		this.desc = desc;
		this.isDone = isDone;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getTarget() {
		return target;
	}
	public void setTarget(Date target) {
		this.target = target;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return String.format("Todo [id=%s, target=%s, desc=%s, isDone=%s]", id, target, desc, isDone);
	}
	
	
}
