package ch.bfh.bti7081.s2020.black.presenters;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.ButtonInterface;
import ch.bfh.bti7081.s2020.black.views.HeaderViewImplementation;
import ch.bfh.bti7081.s2020.black.views.HomeViewImplementation;
import com.vaadin.flow.component.notification.Notification;

public class HeaderPresenter extends Presenter implements ButtonInterface{

    public HeaderPresenter(SuperPresenter superPresenter){

        super(superPresenter);
        currentView = new HeaderViewImplementation(this);
        superPresenter.addView(currentView);
    }

    @Override
    public void buttonClick(String information) {
        if (information == "login"){
            Notification.show("blabla");
        }
    }
}


