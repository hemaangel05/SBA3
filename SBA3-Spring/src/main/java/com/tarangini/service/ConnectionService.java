package com.tarangini.service;

import java.util.List;

import com.tarangini.model.Connection;

public interface ConnectionService {
	public Connection addConnection(Connection con);
	public boolean removeConnection(int cid);
	public Connection updateConnection(Connection con);
	public Connection getConnection(int cid);
	public Connection getConnectionByName(String name);
	public List<Connection> getAllConnections();
	public boolean exists(int cid);
	public boolean existsByName(String name);
}