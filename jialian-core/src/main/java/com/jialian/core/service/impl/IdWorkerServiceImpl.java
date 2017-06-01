package com.jialian.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jialian.api.component.IdWorkerServiceApi;
import com.jialian.core.component.IdWorker;

@Service("idWorkerService")
public class IdWorkerServiceImpl implements IdWorkerServiceApi{

	@Autowired
	private IdWorker idWorker;
	
	@Override
	public long nextId() {
		return idWorker.nextId();
	}

}
