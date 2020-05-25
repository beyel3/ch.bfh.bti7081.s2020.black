package ch.bfh.bti7081.s2020.black.presenters;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.HeaderInterface;
import ch.bfh.bti7081.s2020.black.views.HeaderViewImplementation;

public class HeaderPresenter extends Presenter implements HeaderInterface {

    public HeaderPresenter(SuperPresenter superPresenter){

        super(superPresenter);
        currentView = new HeaderViewImplementation(this);
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

                break;
            case CREATEEVENT:

                break;
            case JOINPUBLICEVENT:

                break;
        }
    }
}


