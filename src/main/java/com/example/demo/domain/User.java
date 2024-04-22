package com.example.demo.domain;

import static lombok.AccessLevel.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import com.example.demo.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PROTECTED)
@Getter
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "interest_id")
	private Interest interest;

	// @OneToMany(mappedBy = "user")
	// private List<SavedQuiz> savedQuizzes = new ArrayList<>();

	private String email;
	private String password;
	private String nickname;
	private Boolean deleteYn;

	@Builder
	public User(Interest interest, String email, String password, String nickname, Boolean deleteYn) {
		this.interest = interest;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.deleteYn = deleteYn;
	}
}
