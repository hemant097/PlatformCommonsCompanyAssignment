package com.platformcommons.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platformcommons.Entities.CurrentSession;

@Repository
public interface CurrentSessionDAO extends JpaRepository<CurrentSession, Integer> {

	public CurrentSession findByUserid(Integer userid);
	
    public CurrentSession findByUniqueid(String key);
}
