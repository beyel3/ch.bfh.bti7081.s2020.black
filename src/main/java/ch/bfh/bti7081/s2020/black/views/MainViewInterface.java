package ch.bfh.bti7081.s2020.black.views;

public interface MainViewInterface {

	interface MainViewListener {
		void buttonClick(String buttonText);
	}
	
	public void addListener(MainViewListener listener);
}
