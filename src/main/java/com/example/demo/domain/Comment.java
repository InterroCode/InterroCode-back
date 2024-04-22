package com.example.demo.domain;

import static lombok.AccessLevel.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.example.demo.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PROTECTED)
@Getter
@Entity
public class Comment extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Long id;

	@Column(name = "comment_writer")
	private String writer;
	private String content;
	private Boolean deleteYn;

	@Builder
	public Comment(String writer, String content, Boolean deleteYn) {
		this.writer = writer;
		this.content = content;
		this.deleteYn = deleteYn;
	}
}
