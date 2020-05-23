package ch.bfh.bti7081.s2020.black.presenters;

import com.vaadin.flow.component.Component;

public abstract class Presenter {
	
	protected SuperPresenter superPresenter;
	protected Component currentView;
	
	public Presenter(SuperPresenter superPresenter) {
		this.superPresenter = superPresenter;
	}
	
}
