package com.capstone.teamProj_10.apiTest.question;

//import com.mysite.sbb.DataNotFoundException;
//import com.mysite.sbb.answer.Answer;
//import com.mysite.sbb.user.SiteUser;
import com.capstone.teamProj_10.apiTest.utils.DataNotFoundException;
import com.capstone.teamProj_10.apiTest.answer.Answer;
import com.capstone.teamProj_10.apiTest.user.SiteUser;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;

	private Specification<Question> search(String kw) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
			query.distinct(true); // 중 복 을 제 거
			Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
			Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
			Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
			return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 제 목
//					cb.like(q.get("category"), "%" + kw + "%"),
//					cb.like(q.get("price"), "%" + kw + "%"),
					cb.like(q.get("content"), "%" + kw + "%"), // 내 용
					cb.like(u1.get("username"), "%" + kw + "%"), // 질 문 작 성 자
					cb.like(a.get("content"), "%" + kw + "%"), // 답 변 내 용
					cb.like(u2.get("username"), "%" + kw + "%")); // 답 변 작 성 자
			}
		};
	}

	
	
	
	public List<Question> getList() {
		return this.questionRepository.findAll();
	}

	public Question getQuestion(Integer id) {
		Optional<Question> question = this.questionRepository.findById(id);
		if (question.isPresent()) {
			return question.get();
		} else {
			throw new DataNotFoundException("question not found");
		}
	}

	public Page<Question> getList(int page, String kw) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		Specification<Question> spec = search(kw);
		return this.questionRepository.findAllByKeyword(kw, pageable);
	}

	public void create(String subject, String content, SiteUser user) {
		Question q = new Question();
		q.setSubject(subject);
//		q.setCategory(category);
//		q.setPrice(price);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		q.setAuthor(user);
		this.questionRepository.save(q);
	}

	public void modify(Question question, String subject, String content) {
		question.setSubject(subject);
//		question.setCategory(category);
//		question.setPrice(price);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		this.questionRepository.save(question);
	}
	
	public void delete(Question question) {
		this.questionRepository.delete(question);
		}
	
	public void vote(Question question, SiteUser siteUser) {
		question.getVoter().add(siteUser);
		this.questionRepository.save(question);
	}
}
