package co.ds.bean;

import java.io.Serializable;

public class Topic implements Serializable {

	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}
}
