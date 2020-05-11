package ch.bfh.bti7081.s2020.black.views;

import java.util.List;

import ch.bfh.bti7081.s2020.black.model.Tag;


public interface EventCreaterView {

	public void setTitle(String title);
	public void setDescription(String description);
	public void setTags(List<Tag> tags);
	public void setRating(double avgRating);

	interface EventCreaterViewListener {
		void buttonClick(String buttonText);

		void buttonClick(char operation);
	}

	public void addListener(EventCreaterViewListener listener);
}
