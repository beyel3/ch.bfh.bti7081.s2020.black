package ch.bfh.bti7081.s2020.black.presenters;

import ch.bfh.bti7081.s2020.black.interfaces.HeaderInterface;
import ch.bfh.bti7081.s2020.black.model.AccountType;
import ch.bfh.bti7081.s2020.black.model.stateModel.JoinPublicEvents;
import ch.bfh.bti7081.s2020.black.views.HeaderViewImplementation;

public class HeaderPresenter extends Presenter implements HeaderInterface {

    private HeaderViewImplementation headerViewImplementation;

    public HeaderPresenter(SuperPresenter superPresenter){

        super(superPresenter);
            this.headerViewImplementation = new HeaderViewImplementation(this, true);
        currentView = this.headerViewImplementation;
        headerViewImplementation.setUsername(superPresenter.getLoggedInUserName());
        superPresenter.addHeader(currentView);
        
    }

    @Override
    public void buttonClick(HeaderAction action) {
    	
    	 superPresenter.clearView();
    	
        switch (action){
            case HOME:
                new HomeViewPresenter(superPresenter);
                break;
            case LOGOUT:
                superPresenter.removeLoggedInAccount();
                superPresenter.removeHeader(currentView);
                new LoginPresenter(superPresenter);
                break;
            case MYEVENTS:
            	new MyEventPresenter(superPresenter);
                break;
            case CREATEEVENT:
            	 new EventTemplatePresenter(superPresenter);
                break;
            case JOINPUBLICEVENT:
            	new JoinPublicEventPresenter(superPresenter);
                break;
            case ADMIN:

                break;
        }
    }
}


