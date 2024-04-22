package com.example.demo;

import java.time.LocalDateTime;

import jakarta.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public class BaseTimeEntity {

	@CreatedDate
	private LocalDateTime createdDateTime;

	@LastModifiedDate
	private LocalDateTime modifiedDateTime;
}
