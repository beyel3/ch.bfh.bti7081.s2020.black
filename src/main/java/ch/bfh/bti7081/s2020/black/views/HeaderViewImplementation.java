package ch.bfh.bti7081.s2020.black.views;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.ButtonInterface;
import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.HeaderInterface;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.theme.lumo.Lumo;

public class HeaderViewImplementation<T extends HeaderInterface> extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private final HorizontalLayout loginButtonLayout;
	private final HorizontalLayout menuBarLayout;
	private final HorizontalLayout menuBar;

	private T presenter;

	public HeaderViewImplementation(T presenter) {

		this.presenter = presenter;

		setMargin(false);
		setSpacing(false);
		setSizeFull();

		// Icons in header and the theme toggle button / home button
		Image logo = new Image("./icons/ProjectLogoBlueSmall.png", "");
		logo.addClickListener(event -> presenter.buttonClick(HeaderInterface.HeaderAction.HOME));
//		Icon iconHome = VaadinIcon.HOME_O.create();
//		Button iconHomeButton = new Button(iconHome);
//		iconHomeButton.addClickListener(event -> iconHomeButton.getUI().ifPresent(ui -> ui.navigate("")));

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

//		LoginOverlay loginOverlay = new LoginOverlay();
//		loginOverlay.addLoginListener(e -> loginOverlay.close());
//		LoginI18n i18n = LoginI18n.createDefault();
//		loginOverlay.setDescription("Login with e-mail and password");
//		loginOverlay.setTitle("BurnOUTEvents");
//		i18n.setAdditionalInformation("To close the login form submit non-empty username and password");
//		loginOverlay.setI18n(i18n);

		menuBar = new HorizontalLayout();

		Button homeButton = new Button("Home");
		homeButton.addClickListener(event -> presenter.buttonClick(HeaderInterface.HeaderAction.HOME));
		menuBar.add(homeButton);
		menuBar.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

		Button createEvent = new Button("Create Event");
		createEvent.addClickListener(event -> presenter.buttonClick(HeaderInterface.HeaderAction.CREATEEVENT));
		menuBar.add(createEvent);

		Button myEvent = new Button("My Events");
		myEvent.addClickListener(event -> presenter.buttonClick(HeaderInterface.HeaderAction.MYEVENTS));
		menuBar.add(myEvent);

		loginButtonLayout = new HorizontalLayout();
		loginButtonLayout.setWidth("100%");
		menuBarLayout = new HorizontalLayout();
		menuBarLayout.setWidth("100%");

		final Div header = new Div();
		Button loginButton = new Button("Login");
		//loginButton.addClickListener(e -> loginOverlay.setOpened(true));
		loginButton.addClickListener(event -> presenter.buttonClick(HeaderInterface.HeaderAction.LOGIN));
		Button signupButton = new Button("SignUp");
		signupButton.addClickListener(event -> presenter.buttonClick(HeaderInterface.HeaderAction.SIGNUP));

		menuBarLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		menuBarLayout.add(menuBar);
		loginButtonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
		loginButtonLayout.add(logo, iconToggleThemeButton, loginButton, signupButton);
		header.setWidth("100%");
		header.setHeight("100px");
		menuBarLayout.getStyle().set("background-color", "#2f6f91");
		header.add(loginButtonLayout, menuBarLayout);

		add(header);
	}

}
