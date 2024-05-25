package com.example.demo.domain;

import static lombok.AccessLevel.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PROTECTED)
@Getter
@Entity
public class QuizScrap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quiz_scrap_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private String subject;
	private String question;

	@Column(name = "saved_answer")
	private String answer;

	private String gptFeedback;

	@Builder
	public QuizScrap(User user, String subject, String question, String answer, String gptFeedback) {
		this.user = user;
		this.subject = subject;
		this.question = question;
		this.answer = answer;
		this.gptFeedback = gptFeedback;
	}
}
