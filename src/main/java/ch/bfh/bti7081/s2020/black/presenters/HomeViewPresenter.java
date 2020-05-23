package ch.bfh.bti7081.s2020.black.presenters;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.HomeInterface;
import ch.bfh.bti7081.s2020.black.model.MainModel;
import ch.bfh.bti7081.s2020.black.views.CreateEventViewImplementaion;
import ch.bfh.bti7081.s2020.black.views.HeaderView;
import ch.bfh.bti7081.s2020.black.views.HomeViewImplementation;
import ch.bfh.bti7081.s2020.black.views.MainView;
import com.vaadin.flow.component.notification.Notification;

public class HomeViewPresenter extends SuperPresenter implements HomeInterface.HomePresenterInterface {

    public HomeViewPresenter(MainView mainView, MainModel mainModel){
        super(mainView, mainModel);
        HomeViewImplementation homeViewImplementation = new HomeViewImplementation(this);
        mainView.add(new HeaderView());
        mainView.add(homeViewImplementation);
    }

    @Override
    public void route(String target) {
        mainView.remove();

    }
}
