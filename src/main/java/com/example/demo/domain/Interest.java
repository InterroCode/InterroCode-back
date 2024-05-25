package com.example.demo.domain;

import static lombok.AccessLevel.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PROTECTED)
@Getter
@Entity
public class Interest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "interest_id")
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id")
    private User user;

	private String task;
	private String mainLanguage;
	private String subLanguage;
	private String mainFramework;
	private String subFramework;

	@Builder
	public Interest(User user, String task, String mainLanguage, String subLanguage, String mainFramework, String subFramework) {
		this.user = user;
		this.task = task;
		this.mainLanguage = mainLanguage;
		this.subLanguage = subLanguage;
		this.mainFramework = mainFramework;
		this.subFramework = subFramework;
	}
}
