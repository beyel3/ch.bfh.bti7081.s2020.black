package ch.bfh.bti7081.s2020.black.presenters;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.CloseEventViewInterface;
import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventViewInterface.EventAction;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.Status;
import ch.bfh.bti7081.s2020.black.model.stateModel.CloseEvent;
import ch.bfh.bti7081.s2020.black.views.MarkEventDoneViewImplementation;

public class CloseEventPresenter extends Presenter implements CloseEventViewInterface {
	
	private Event event;
	private CloseEvent closeEventState; 

	public CloseEventPresenter(SuperPresenter superPresenter, Event event) {
		super(superPresenter);
		
		this.event = event;

		closeEventState = new CloseEvent();
		superPresenter.setState(closeEventState);

		currentView = new MarkEventDoneViewImplementation<CloseEventViewInterface>(this);
		superPresenter.addPage(currentView);

	}

	@Override
	public Event getEvent() {
		return event;
	}


	public byte[] getPicture() throws IOException {
		
		return null;
	}

	@Override
	public void closeEvent(byte[] picture, int rating) {
		
		event.setRating(rating);
		event.setStatus(Status.done);
		
		closeEventState.savePicture(picture);
		closeEventState.setEventRating(event);
		closeEventState.setEventStatus(event);
		
		superPresenter.removePage(currentView);
		new MyEventPresenter(superPresenter);
	}
}
