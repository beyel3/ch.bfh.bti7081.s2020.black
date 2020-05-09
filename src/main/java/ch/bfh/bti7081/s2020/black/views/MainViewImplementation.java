package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MainViewImplementation extends VerticalLayout implements MainViewInterface {
	
	List<MainViewListener> listeners = new ArrayList<MainViewListener>();
	
	public MainViewImplementation() {
	      
	      HorizontalLayout layout_1 = new HorizontalLayout();
	      layout_1.add(createButton("CREATE EVENT"));
	      layout_1.add(createButton("BROWSE EVENT TEMPLATES"));
	      layout_1.add(createButton("JOIN PUBLIC EVENT"));
	      layout_1.add(createButton("MY ACCOUNT"));
	      add(layout_1);
	      
	      }
	
	private Button createButton(String buttonText) {
		return new Button(buttonText, event -> {
					for (MainViewListener listener : listeners)
						listener.buttonClick(event.getSource().getText());
				});
			}

	@Override
	public void addListener(MainViewListener listener) {
		listeners.add(listener);
	}
}
