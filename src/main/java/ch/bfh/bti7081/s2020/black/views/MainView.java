package ch.bfh.bti7081.s2020.black.views;

<<<<<<< HEAD
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
=======
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.dependency.CssImport;
>>>>>>> Luca
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
<<<<<<< HEAD
=======
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout.Orientation;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
>>>>>>> Luca
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import ch.bfh.bti7081.s2020.black.presenters.EventPresenter;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
@Theme(value = Lumo.class, variant = Lumo.DARK)
<<<<<<< HEAD

public class MainView extends VerticalLayout {
	
	public static final EventPresenter eventPresenter =  new EventPresenter();
	private final VerticalLayout contentLayout;
	
    public MainView() {
    	
    	contentLayout = new VerticalLayout();
		final FormLayout mainFormLayout = new FormLayout();
		
        Button createEventButton = new Button("Create Event");
        createEventButton.addClickListener(event -> 
        		getUI().ifPresent(ui ->  ui.navigate("EventCreaterView/")));
        
        Button browseEventButton = new Button("Create From Template Eventtemplates");
        browseEventButton.addClickListener(event -> 
        		browseEventButton.getUI().ifPresent(ui ->  ui.navigate("EventCreaterView/CreatedFromTemplate")));
        
		contentLayout.setAlignItems(Alignment.CENTER);
		contentLayout.setAlignSelf(Alignment.END);       
        mainFormLayout.add(createEventButton, browseEventButton);
 
		final Div header = new Div();
		header.setText("Test");
		header.setClassName("header");
		header.setHeight("50px");

		contentLayout.add(mainFormLayout);
		add(header);
		add(contentLayout);
        
    }
    
=======
@CssImport("./styles/shared-styles.css")

public class MainView extends VerticalLayout {
>>>>>>> Luca

	private static final long serialVersionUID = 1L;

	public static final EventPresenter eventPresenter = new EventPresenter();
	private final HorizontalLayout contentLayoutFirstRow;
	private final HorizontalLayout contentLayoutSecondRow;
	private final HorizontalLayout loginButtonLayout;
	private final HorizontalLayout menuBarLayout;
	private final MenuBar menuBar;

	public MainView() {

		setMargin(false);
		setSpacing(false);
		setSizeFull();
		Icon iconHome = VaadinIcon.HOME_O.create();
		Button iconHomeButton = new Button(iconHome);
		
		Icon iconTheme = VaadinIcon.SUN_O.create();
		
		iconHomeButton.addClickListener(event -> iconHomeButton.getUI().ifPresent(ui -> ui.navigate("")));
		
		Button iconToggleThemeButton = new Button(iconTheme, click -> {
			ThemeList themeList = UI.getCurrent().getElement().getThemeList(); //

			if (themeList.contains(Lumo.DARK)) { //
				themeList.remove(Lumo.DARK);
			} else {
				themeList.add(Lumo.DARK);
			}
			if (themeList.contains(Lumo.LIGHT)) { //
				themeList.remove(Lumo.LIGHT);
			} else {
				themeList.add(Lumo.LIGHT);
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

		contentLayoutFirstRow = new HorizontalLayout();
		contentLayoutFirstRow.setWidth("100%");
		contentLayoutFirstRow.setHeight("35%");

		contentLayoutSecondRow = new HorizontalLayout();
		contentLayoutSecondRow.setWidth("100%");
		contentLayoutSecondRow.setHeight("35%");

		Button createEventButton = new Button("CREATE EVENT");
		createEventButton.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("EventCreaterView")));
		createEventButton.getStyle().set("height", "90%");
		createEventButton.getStyle().set("width", "40%");
		createEventButton.getStyle().set("background-color", "#336699");
		createEventButton.getStyle().set("font-size", "20px");
		createEventButton.getStyle().set("font-size", "2vw");

		Button browseEventButton = new Button("CREATE EVENT FROM TEMPLATE");
		browseEventButton.addClickListener(event -> browseEventButton.getUI()
				.ifPresent(ui -> ui.navigate("EventCreaterView/CreatedFromTemplate")));
		browseEventButton.getStyle().set("height", "90%");
		browseEventButton.getStyle().set("width", "40%");
		browseEventButton.getStyle().set("background-color", "#336699");
		browseEventButton.getStyle().set("font-size", "20px");
		browseEventButton.getStyle().set("font-size", "2vw");

		Button searchOpenPublicEventButton = new Button("SEARCH OPEN PUBLIC EVENTS");
		searchOpenPublicEventButton.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("EventSearchView")));
		searchOpenPublicEventButton.getStyle().set("height", "90%");
		searchOpenPublicEventButton.getStyle().set("width", "40%");
		searchOpenPublicEventButton.getStyle().set("background-color", "#336699");
		searchOpenPublicEventButton.getStyle().set("font-size", "20px");
		searchOpenPublicEventButton.getStyle().set("font-size", "2vw");

		Button myEventsButton = new Button("MY EVENTS");
		myEventsButton.addClickListener(event -> browseEventButton.getUI().ifPresent(ui -> ui.navigate("")));
		myEventsButton.getStyle().set("height", "90%");
		myEventsButton.getStyle().set("width", "40%");
		myEventsButton.getStyle().set("background-color", "#336699");
		myEventsButton.getStyle().set("font-size", "20px");
		myEventsButton.getStyle().set("font-size", "2vw");

		contentLayoutFirstRow.add(createEventButton, browseEventButton);
		contentLayoutFirstRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		contentLayoutSecondRow.add(searchOpenPublicEventButton, myEventsButton);
		contentLayoutSecondRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		contentLayoutSecondRow.setDefaultVerticalComponentAlignment(Alignment.STRETCH);

		// Header with the login / singup button
		final Div header = new Div();
		Button loginButton = new Button("Login");
		loginButton.addClickListener(e -> loginOverlay.setOpened(true));
		Button signupButton = new Button("SignUp");
		signupButton.addClickListener(event -> signupButton.getUI().ifPresent(ui -> ui.navigate("")));

		// Align the buttons and other elements of the vertical layout at the end of the
		// header (top right)

		menuBarLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		menuBarLayout.add(menuBar);
		loginButtonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
		loginButtonLayout.add(iconHomeButton, iconToggleThemeButton, loginButton, signupButton);
		header.setWidth("100%");
		header.setHeight("100px");

		menuBarLayout.getStyle().set("background-color", "#D9E6F2");
//		header.getStyle().set("border", "1px solid #9E9E9E");
//		contentLayoutFirstRow.getStyle().set("border", "1px solid #9E9E9E");
//		contentLayoutSecondRow.getStyle().set("border", "1px solid #9E9E9E");
//		loginButtonLayout.getStyle().set("border", "1px solid #9E9E9E");
		header.add(loginButtonLayout, menuBarLayout);

		add(header);
		add(contentLayoutFirstRow);
		add(contentLayoutSecondRow);

	}

}
