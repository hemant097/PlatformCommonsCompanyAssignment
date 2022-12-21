package com.platformcommons.Entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentSession {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@Column(unique = true)
	private Integer userid;
	
	private String uniqueid;
	
	private LocalDateTime time;

	public CurrentSession(Integer userid, String uniqueId, LocalDateTime time) {
		super();
		this.userid = userid;
		this.uniqueid = uniqueId;
		this.time = time;
	}
}
