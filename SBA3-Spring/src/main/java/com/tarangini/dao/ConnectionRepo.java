package com.tarangini.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tarangini.model.Connection;

@Repository
public interface ConnectionRepo extends JpaRepository<Connection, Integer> {
	void deleteByCid(int cid);
	Optional<Connection> findByCid(int cid);
	boolean existsByCid(int cid);
	Optional<Connection> findByName(String name);
	boolean existsByName(String name);
}
