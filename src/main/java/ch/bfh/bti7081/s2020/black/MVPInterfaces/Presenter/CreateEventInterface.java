package ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter;

import java.util.Set;

import ch.bfh.bti7081.s2020.black.model.Account;

public interface CreateEventInterface {
	
	public void submit(Boolean isPublic, int maxParticipants, Set<Account> set);


}
