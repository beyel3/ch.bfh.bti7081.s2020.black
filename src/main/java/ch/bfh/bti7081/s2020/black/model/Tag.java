package ch.bfh.bti7081.s2020.black.model;

public class Tag {
	
	private int id;
	private String tag;
	
	public Tag(int id, String tag) {
		this.id = id;
		this.tag = tag;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getTagName() {
		return this.tag;
	}

	public String toString() {
		return tag;
	}
}
