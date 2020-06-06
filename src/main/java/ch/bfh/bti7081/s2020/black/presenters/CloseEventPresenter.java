package ch.bfh.bti7081.s2020.black.presenters;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventViewInterface.EventAction;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.stateModel.CloseEvent;
import ch.bfh.bti7081.s2020.black.views.CloseEventViewInterface;

public class CloseEventPresenter extends Presenter implements CloseEventViewInterface {

	public CloseEventPresenter(SuperPresenter superPresenter) {
		super(superPresenter);

		CloseEvent closeEventState = new CloseEvent();
		superPresenter.setState(closeEventState);



	}

	@Override
	public Event getEvent() {

		return closeEventState.getEvent();
	}

	@Override
	public void buttonClick(EventAction openchat, ClickEvent<Button> event) {

	}

	public byte[] getImageBytes() throws IOException {
		
		File fnew = new File("/tmp/rose.jpg");
		BufferedImage originalImage = ImageIO.read(fnew);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(originalImage, "jpg", baos);
		byte[] imageInByte = baos.toByteArray();
		return imageInByte;
	}

}
