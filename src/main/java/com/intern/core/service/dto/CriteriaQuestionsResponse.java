package com.intern.core.service.dto;

import java.util.List;

public class CriteriaQuestionsResponse {

	private CriteriaResponse criteria;
	
	private List<QuestionResponse> questions;

	public CriteriaResponse getCriteria() {
		return criteria;
	}

	public void setCriteria(CriteriaResponse criteria) {
		this.criteria = criteria;
	}

	public List<QuestionResponse> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionResponse> questions) {
		this.questions = questions;
	}
}
