package ch.bfh.bti7081.s2020.black.MVPInterfaces;

public interface HomeInterface {
    interface HomeViewInterface{

    }
    interface HomePresenterInterface{
        public void route(String target);
    }
}
