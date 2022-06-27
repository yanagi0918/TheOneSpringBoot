package com.theone.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theone.springboot.entity.Event;

public interface EventDao extends JpaRepository<Event, Integer> {

}
