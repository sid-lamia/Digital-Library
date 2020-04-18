package com.fdmgroup.dao;

import java.sql.SQLException;

import com.fdmgroup.model.IStorable;

public interface IRemovable<T extends IStorable> {
	public void remove(T t);
}
