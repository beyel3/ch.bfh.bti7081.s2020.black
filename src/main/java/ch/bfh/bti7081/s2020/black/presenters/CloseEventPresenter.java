package ch.bfh.bti7081.s2020.black.presenters;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.CloseEventViewInterface;
import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventViewInterface.EventAction;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.stateModel.CloseEvent;
import ch.bfh.bti7081.s2020.black.views.MarkEventDoneViewImplementation;

public class CloseEventPresenter extends Presenter implements CloseEventViewInterface {
	
	private Event event;

	public CloseEventPresenter(SuperPresenter superPresenter, Event event) {
		super(superPresenter);
		
		this.event = event;

		CloseEvent closeEventState = new CloseEvent();
		superPresenter.setState(closeEventState);

		currentView = new MarkEventDoneViewImplementation<CloseEventViewInterface>(this);
		superPresenter.addPage(currentView);

	}

	@Override
	public Event getEvent() {
		return event;
	}


	public byte[] getPicture() throws IOException {
		
		File fnew = new File("/tmp/rose.jpg");
		BufferedImage originalImage = ImageIO.read(fnew);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(originalImage, "jpg", baos);
		byte[] imageInByte = baos.toByteArray();
		return imageInByte;
	}

	@Override
	public void buttonClick(EventAction action) {
		
		
	}

}
