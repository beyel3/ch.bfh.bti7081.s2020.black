package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "MyEvents", layout = MainView.class)
@RouteAlias(value = "AccountView", layout = MainView.class)
@PageTitle("MyEvents")

public class AccountView extends VerticalLayout {
	
	public AccountView() {
		
	}

}
