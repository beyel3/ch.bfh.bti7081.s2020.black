package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import ch.bfh.bti7081.s2020.black.interfaces.JoinPublicEventInterface;
import ch.bfh.bti7081.s2020.black.model.Account;

public class AddPatientView<T extends JoinPublicEventInterface> extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;

	public AddPatientView(T presenter) {
		
		MultiSelectListBox<Account> participants = new MultiSelectListBox<Account>();
		participants.setItems(presenter.getFriends());
		
		FormLayout FormLayout = new FormLayout();		
		FormLayout.addFormItem(participants, "Add Patient:");
		
		Button save = new Button("JOIN EVENT");
		save.getStyle().set("marginRight", "10px");

		save.addClickListener(event -> {
			presenter.joinPublicEvent(participants.getSelectedItems());
		});
		add(FormLayout, save);
	}
}
