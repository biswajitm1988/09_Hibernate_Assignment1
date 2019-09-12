package com.fsd.example.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SUBJECT")
public class Subject implements Comparable<Subject>, Serializable {

	private static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment") 
	@Column(name = "SUBJECTID")
	private Long subjectId;

	@Column(name = "SUBTITLE")
	private String subtitle;

	@Column(name = "DURATION_IN_HOURS")
	private int durationInHours;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "SUBJECT_BOOK ", joinColumns = @JoinColumn(name = "SUBJECTID"), inverseJoinColumns = @JoinColumn(name = "BOOKID"))
	private List<Book> references;

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public int getDurationInHours() {
		return durationInHours;
	}

	public void setDurationInHours(int durationInHours) {
		this.durationInHours = durationInHours;
	}

	public List<Book> getReferences() {
		return references;
	}

	public void setReferences(List<Book> references) {
		this.references = references;
	}

	@Override
	public int compareTo(Subject subject) {
		return this.subjectId.compareTo(subject.getSubjectId());
	}

	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subtitle=" + subtitle + ", durationInHours=" + durationInHours
				+ ", references=" + references + "]";
	}
}
