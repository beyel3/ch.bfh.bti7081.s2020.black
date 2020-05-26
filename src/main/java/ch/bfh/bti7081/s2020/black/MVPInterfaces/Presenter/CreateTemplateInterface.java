package ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter;

import java.util.Set;

import ch.bfh.bti7081.s2020.black.model.Tag;

public interface CreateTemplateInterface {

	public void submit(String title, String description, Set<Tag> tags);
}
