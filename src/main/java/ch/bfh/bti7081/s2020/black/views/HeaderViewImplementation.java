package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.theme.lumo.Lumo;

import ch.bfh.bti7081.s2020.black.interfaces.HeaderInterface;
import ch.bfh.bti7081.s2020.black.interfaces.UserNameInterface;

public class HeaderViewImplementation<T extends HeaderInterface> extends VerticalLayout implements UserNameInterface {

	private static final long serialVersionUID = 1L;
	private final HorizontalLayout rightLayout;
	private final HorizontalLayout menuBarLayout;
	private final HorizontalLayout menuBar;
	//private Label userName = new Label("");
	private Button userName = new Button("");

	private T presenter;

	public HeaderViewImplementation(T presenter, boolean admin) {

		this.presenter = presenter;

		setMargin(false);
		setSpacing(false);
		setSizeFull();


		Image logo = new Image("./icons/ProjectLogoBlueSmall.png", "");
		Button logOutButton = new Button("Logout");


		menuBar = new HorizontalLayout();
		menuBar.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

		Button homeButton = new Button("Home");
		Button createEvent = new Button("Create Event");
		Button myEvent = new Button("My Events");

		Icon iconTheme = VaadinIcon.SUN_O.create();
		Button iconToggleThemeButton = new Button(iconTheme, click -> {
			ThemeList themeList = UI.getCurrent().getElement().getThemeList();
			if (themeList.contains(Lumo.LIGHT)) {
				themeList.remove(Lumo.LIGHT);
			} else {
				themeList.add(Lumo.LIGHT);
			}
			if (themeList.contains(Lumo.DARK)) {
				themeList.remove(Lumo.DARK);
			} else {
				themeList.add(Lumo.DARK);
			}
		});

		//Click Listeners
		logo.addClickListener(event -> presenter.buttonClick(HeaderInterface.HeaderAction.HOME));
		logOutButton.addClickListener(event -> presenter.buttonClick(HeaderInterface.HeaderAction.LOGOUT));
		homeButton.addClickListener(event -> presenter.buttonClick(HeaderInterface.HeaderAction.HOME));
		createEvent.addClickListener(event -> presenter.buttonClick(HeaderInterface.HeaderAction.CREATEEVENT));
		myEvent.addClickListener(event -> presenter.buttonClick(HeaderInterface.HeaderAction.MYEVENTS));
		userName.addClickListener(event -> presenter.buttonClick(HeaderInterface.HeaderAction.MYEVENTS));

		menuBar.add(homeButton);
		menuBar.add(createEvent);
		menuBar.add(myEvent);

		menuBarLayout = new HorizontalLayout();
		menuBarLayout.setWidth("100%");
		menuBarLayout.getStyle().set("background-color", "#2f6f91");
		menuBarLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		menuBarLayout.add(menuBar);

		rightLayout = new HorizontalLayout();
		rightLayout.setWidth("100%");
		rightLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
		if (admin) {
			rightLayout.add(logo, iconToggleThemeButton, logOutButton, userName);
		} else {
			rightLayout.add(logo, iconToggleThemeButton, logOutButton, userName);
		}
		final Div header = new Div();
		header.setWidth("100%");
		header.setHeight("100px");

		header.add(rightLayout, menuBarLayout);

		add(header);
	}

	@Override
	public void setUsername(String username) {
		this.userName.setText(username);
	}
}
