package com.fdmgroup.dao;

import java.sql.SQLException;
import java.util.List;

import com.fdmgroup.model.IStorable;

public interface IStorage<T extends IStorable> {
	public void create(T t);
	public T findById(int id);
	public List<T> findAll();
}
