package com.tarangini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarangini.dao.ConnectionRepo;
import com.tarangini.model.Connection;

@Service
public class ConnectionServiceImp implements ConnectionService {
	
	@Autowired
	public ConnectionRepo conRepo;

	@Override
	public Connection addConnection(Connection con) {
		return conRepo.save(con);
	}

	@Override
	public boolean removeConnection(int cid) {
		if(conRepo.existsByCid(cid)) {
			conRepo.deleteByCid(cid);
			return true;
		}
		return false;
	}

	@Override
	public Connection updateConnection(Connection con) {
			return conRepo.save(con);
	}

	@Override
	public Connection getConnection(int cid) {
		Optional<Connection> opt = conRepo.findByCid(cid);
		return opt.isPresent()?opt.get():null;
	}

	@Override
	public List<Connection> getAllConnections() {
		return conRepo.findAll();
	}

	@Override
	public boolean exists(int cid) {
		return conRepo.existsByCid(cid);
	}

	@Override
	public Connection getConnectionByName(String name) {
		Optional<Connection> opt = conRepo.findByName(name);
		return opt.isPresent()?opt.get():null;
	}

	@Override
	public boolean existsByName(String name) {
		return conRepo.existsByName(name);
	}

}