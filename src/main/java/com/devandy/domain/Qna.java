package com.devandy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Qna {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardId;

	@JoinColumn(foreignKey = @ForeignKey(name="fk_qna_author"))
	private Long authorId;
	
	private String authorName;
	
	private String createdDate;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private String contents;

}
