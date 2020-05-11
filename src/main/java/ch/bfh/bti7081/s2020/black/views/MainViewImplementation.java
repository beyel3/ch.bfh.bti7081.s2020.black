package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.theme.lumo.Lumo;


public class MainViewImplementation extends VerticalLayout {

		private static final long serialVersionUID = 1L;

		private final HorizontalLayout loginButtonLayout;
		private final HorizontalLayout menuBarLayout;
		private final MenuBar menuBar;

		public MainViewImplementation() {

			setMargin(false);
			setSpacing(false);
			setSizeFull();
			Icon iconHome = VaadinIcon.HOME_O.create();
			Button iconHomeButton = new Button(iconHome);

			Icon iconTheme = VaadinIcon.SUN_O.create();

			iconHomeButton.addClickListener(event -> iconHomeButton.getUI().ifPresent(ui -> ui.navigate("")));
			
			Button iconToggleThemeButton = new Button(iconTheme, click -> {
				ThemeList themeList = UI.getCurrent().getElement().getThemeList(); //
				if (themeList.contains(Lumo.LIGHT)) { //
					themeList.remove(Lumo.LIGHT);
				} else {
					themeList.add(Lumo.LIGHT);
				}
				if (themeList.contains(Lumo.DARK)) { //
					themeList.remove(Lumo.DARK);
				} else {
					themeList.add(Lumo.DARK);
				}

			});

			LoginOverlay loginOverlay = new LoginOverlay();
			loginOverlay.addLoginListener(e -> loginOverlay.close());
			LoginI18n i18n = LoginI18n.createDefault();
			loginOverlay.setDescription("Login with e-mail and password");
			loginOverlay.setTitle("BurnOUTEvents");
			i18n.setAdditionalInformation("To close the login form submit non-empty username and password");
			loginOverlay.setI18n(i18n);


			menuBar = new MenuBar();
			MenuItem home = menuBar.addItem("Home");
			MenuItem createEvent = menuBar.addItem("Create Event");
			MenuItem myEvent = menuBar.addItem("My Events");
			MenuItem searchEvent = menuBar.addItem("Search Events");

			SubMenu searchEventSubMenu = searchEvent.getSubMenu();
			MenuItem publicEvents = searchEventSubMenu.addItem("Search open public Events");
			MenuItem eventTemplate = searchEventSubMenu.addItem("Search Event Templates");
			// menuBar.getStyle().set("background-color", "#336699");

			loginButtonLayout = new HorizontalLayout();
			loginButtonLayout.setWidth("100%");
			menuBarLayout = new HorizontalLayout();
			menuBarLayout.setWidth("100%");



			// Header with the login / singup button
			final Div header = new Div();
			Button loginButton = new Button("Login");
			loginButton.addClickListener(e -> loginOverlay.setOpened(true));
			Button signupButton = new Button("SignUp");
			signupButton.addClickListener(event -> signupButton.getUI().ifPresent(ui -> ui.navigate("")));

			// Align the buttons and other elements of the vertical layout at the end of the
			// header (top right)

//			menuBarLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
			menuBarLayout.add(menuBar);
//			loginButtonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
			loginButtonLayout.add(iconHomeButton, iconToggleThemeButton, loginButton, signupButton);
			header.setWidth("100%");
			header.setHeight("100px");

			menuBarLayout.getStyle().set("background-color", "#D9E6F2");
//			header.getStyle().set("border", "1px solid #9E9E9E");
//			contentLayoutFirstRow.getStyle().set("border", "1px solid #9E9E9E");
//			contentLayoutSecondRow.getStyle().set("border", "1px solid #9E9E9E");
//			loginButtonLayout.getStyle().set("border", "1px solid #9E9E9E");
			
			header.add(loginButtonLayout, menuBarLayout);
			add(header);		
}
}
