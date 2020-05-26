package ch.bfh.bti7081.s2020.black.presenters;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.HeaderInterface;
import ch.bfh.bti7081.s2020.black.model.stateModel.JoinPublicEvents;
import ch.bfh.bti7081.s2020.black.views.HeaderViewImplementation;

public class HeaderPresenter extends Presenter implements HeaderInterface {

    private HeaderViewImplementation headerViewImplementation;

    public HeaderPresenter(SuperPresenter superPresenter){

        super(superPresenter);
        this.headerViewImplementation = new HeaderViewImplementation(this);
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
            case LOGIN:
                new LoginPresenter(superPresenter);
                break;
            case SIGNUP:
                new SignUpPresenter(superPresenter);
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
        }
    }
}


