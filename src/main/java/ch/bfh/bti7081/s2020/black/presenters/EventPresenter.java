package ch.bfh.bti7081.s2020.black.presenters;

import java.util.Collection;

import ch.bfh.bti7081.s2020.black.model.HardCoded;
import ch.bfh.bti7081.s2020.black.model.Tag;

public class EventPresenter {

	private HardCoded hardCoded;

	public EventPresenter() {
		hardCoded = new HardCoded();
	}

	public Collection<Tag> getTags() {
		return hardCoded.getTags();
	}

}
