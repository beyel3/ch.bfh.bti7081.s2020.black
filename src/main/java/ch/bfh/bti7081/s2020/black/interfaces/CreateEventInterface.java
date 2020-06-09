package ch.bfh.bti7081.s2020.black.interfaces;

import java.util.ArrayList;
import java.util.Set;

import ch.bfh.bti7081.s2020.black.model.Account;

public interface CreateEventInterface {
	
	public void submit(Boolean isPublic, int maxParticipants, Set<Account> set);

	public ArrayList<Account> getFriendsFromLoggedInAccount();


}
