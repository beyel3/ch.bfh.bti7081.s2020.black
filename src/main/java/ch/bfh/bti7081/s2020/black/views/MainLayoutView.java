package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;


@Theme(value = Lumo.class, variant = Lumo.DARK)

public class MainLayoutView extends Composite<VerticalLayout> implements HasComponents, RouterLayout {

	private static final long serialVersionUID = 1L;
	private Div childWrapper = new Div();
	private final HorizontalLayout loginButtonLayout;
	private final HorizontalLayout menuBarLayout;
	private final MenuBar menuBar;

	public MainLayoutView() {
		getContent().setSizeFull();

		Icon iconHome = VaadinIcon.HOME_O.create();
		Button iconHomeButton = new Button(iconHome);
		iconHomeButton.addClickListener(event -> iconHomeButton.getUI().ifPresent(ui -> ui.navigate("home")));
		
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

		LoginOverlay loginOverlay = new LoginOverlay();
		loginOverlay.addLoginListener(e -> loginOverlay.close());
		LoginI18n i18n = LoginI18n.createDefault();
		loginOverlay.setDescription("Login with e-mail and password");
		loginOverlay.setTitle("BurnOUTEvents");
		i18n.setAdditionalInformation("To close the login form submit non-empty username and password");
		loginOverlay.setI18n(i18n);

		LoginForm loginForm = new LoginForm();
//		component.addLoginListener(e -> {
//		    boolean isAuthenticated = authenticate(e);
//		    if (isAuthenticated) {
//		        navigateToMainPage();
//		    } else {
//		        component.setError(true);
//		    }
//		});

		menuBar = new MenuBar();
		MenuItem home = menuBar.addItem(new RouterLink("Home", MainView.class));
		MenuItem createEvent = menuBar.addItem(new RouterLink("Create Event", EventCreaterView.class));
		MenuItem myEvent = menuBar.addItem(new RouterLink("My Events", AccountView.class));
		MenuItem searchEvent = menuBar.addItem(new RouterLink("Search Events", EventSearchView.class));

//		SubMenu searchEventSubMenu = searchEvent.getSubMenu();
//		MenuItem publicEvents = searchEventSubMenu.addItem("Search open public Events");
//		MenuItem eventTemplate = searchEventSubMenu.addItem("Search Event Templates");

		loginButtonLayout = new HorizontalLayout();
		loginButtonLayout.setWidth("100%");
		menuBarLayout = new HorizontalLayout();
		menuBarLayout.setWidth("100%");

		final Div header = new Div();
		//H1 header = new H1("Header");
		Button loginButton = new Button("Login");
		loginButton.addClickListener(e -> loginOverlay.setOpened(true));
		Button signupButton = new Button("SignUp");
		signupButton.addClickListener(event -> signupButton.getUI().ifPresent(ui -> ui.navigate("")));

		HorizontalLayout mainContent = new HorizontalLayout();

		menuBarLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		menuBarLayout.add(menuBar);
		loginButtonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
		loginButtonLayout.add(iconHomeButton, iconToggleThemeButton, loginButton, signupButton);
		header.setWidth("100%");
		header.setHeight("100px");

		menuBarLayout.getStyle().set("background-color", "#2f6f91");
		header.add(loginButtonLayout, menuBarLayout);

		mainContent.add(childWrapper);
		mainContent.setFlexGrow(1, childWrapper);
		
		H1 footer = new H1("Footer Test");
		
		add(header);
		add(mainContent);
		add(footer);

		getContent().setFlexGrow(1, mainContent);
		getContent().setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, header);
		getContent().setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, footer);
		getContent().setHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH, mainContent);
	}

	@Override
	public void showRouterLayoutContent(HasElement content) {
		childWrapper.getElement().appendChild(content.getElement());
	}
}
