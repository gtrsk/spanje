package com.abcd.efg.components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class RequestRepositoryService implements RequestRepository{

	@Override
	public List<Request> findRequests(String username) {
		List<Request> result = new ArrayList<>();
		for (int i = 0; i < 20; i++)
		{
			result.add(new Request("username", "i@t.com"));
		}
		return result;
	}

}
