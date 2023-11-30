package com.capstone.teamProj_10.apiTest.question;

//import com.mysite.sbb.answer.Answer;
//import com.mysite.sbb.user.SiteUser;
import com.capstone.teamProj_10.apiTest.answer.Answer;
import com.capstone.teamProj_10.apiTest.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 200)
	private String subject;

	@Column(columnDefinition = "TEXT")
	private String content;

//	@Column(length = 50)
//	private String category;
//
//	@Column(length = 50)
//	private String price;
	
	private LocalDateTime createDate;

	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
	
	@ManyToOne
	private SiteUser author;
	
	private LocalDateTime modifyDate;
	
	@ManyToMany
	Set<SiteUser> voter;
}
