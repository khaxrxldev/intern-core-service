package com.intern.core.service.dto;

import java.util.List;

public class EvaluationCriteriasResponse {

	private EvaluationResponse evaluation;
	
	private List<CriteriaQuestionsResponse> criterias;

	public EvaluationResponse getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(EvaluationResponse evaluation) {
		this.evaluation = evaluation;
	}

	public List<CriteriaQuestionsResponse> getCriterias() {
		return criterias;
	}

	public void setCriterias(List<CriteriaQuestionsResponse> criterias) {
		this.criterias = criterias;
	}
}
