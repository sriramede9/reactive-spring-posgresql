package com.sri.reactive.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;

@Table("anime")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Anime implements Persistable<Integer> {

	@Id
	private Integer id;

	@NotNull(message = "Name should not be null")
	private String name;

	@Transient
	private boolean newAnime;

	public Anime(Integer id, @NotNull(message = "Name should not be null") String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Anime [id=" + id + ", name=" + name + "]";
	}

	@Override
	@Transient
	public boolean isNew() {
		// TODO Auto-generated method stub
		return this.newAnime || id == null;

	}

	public Anime setAsNew() {
		this.newAnime = true;
		return this;
	}

}
