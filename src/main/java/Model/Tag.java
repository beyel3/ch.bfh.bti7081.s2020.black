package Model;

public class Tag {
	
	private int id;
	private String tag;
	
	public Tag(int id, String tag) {
		this.id = id;
		this.tag = tag;
	}
	
	public String toString() {
		return tag;
	}
}
