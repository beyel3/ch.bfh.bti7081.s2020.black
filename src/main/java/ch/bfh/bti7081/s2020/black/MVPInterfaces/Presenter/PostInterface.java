package ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter;

import java.util.ArrayList;

import ch.bfh.bti7081.s2020.black.model.Post;

public interface PostInterface {
	
	public void openChat();
	public ArrayList<Post> getPosts();
	public void submitPost(String post);
	
}
