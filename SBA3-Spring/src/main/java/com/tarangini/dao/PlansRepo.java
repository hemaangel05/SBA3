package com.tarangini.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tarangini.model.Plan;

@Repository
public interface PlansRepo extends JpaRepository<Plan, String> {
	Plan findBypTitle(String pTitle);
	List<Plan> findAllBySpeed(int speed);
	List<Plan> findAllByMaxUsage(int maxUsage);
}
