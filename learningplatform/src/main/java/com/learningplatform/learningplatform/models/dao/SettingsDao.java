package com.learningplatform.learningplatform.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.learningplatform.learningplatform.models.Settings;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface SettingsDao extends CrudRepository<Settings, Integer> {

	public List<Settings> findAll();

	public Settings findByUid(Integer uid);
	
}
