package com.abcd.efg.components;

import java.util.List;


import com.abcd.efg.components.Request;

public interface RequestRepository {
	public List<Request> findRequests(String username);
}