package ch.bfh.bti7081.s2020.black.presenters;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.HeaderInterface;
import ch.bfh.bti7081.s2020.black.views.HeaderViewImplementation;

public class HeaderPresenter extends Presenter implements HeaderInterface {

    private HeaderViewImplementation headerViewImplementation;

    public HeaderPresenter(SuperPresenter superPresenter){

        super(superPresenter);
        this.headerViewImplementation = new HeaderViewImplementation(this);
        currentView = this.headerViewImplementation;
        superPresenter.addHeader(currentView);
    }

    /*
    @Override
    public void buttonClick(String information) {
        if (information == "login"){
            superPresenter.clearView();
            new LoginPresenter(superPresenter);
        } else if (information == "signup"){
            superPresenter.clearView();
            new SignUpPresenter(superPresenter);
        }
    }
     */

    @Override
    public void buttonClick(HeaderAction action) {
        switch (action){
            case HOME:
                superPresenter.clearView();
                new HomeViewPresenter(superPresenter);
                break;
            case LOGIN:
                superPresenter.clearView();
                new LoginPresenter(superPresenter);
                break;
            case SIGNUP:
                superPresenter.clearView();
                new SignUpPresenter(superPresenter);
                break;
            case MYEVENTS:
                headerViewImplementation.setUsername("HANS");
                break;
            case CREATEEVENT:

                break;
            case JOINPUBLICEVENT:

                break;
        }
    }
}


