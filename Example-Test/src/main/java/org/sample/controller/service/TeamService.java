package org.sample.controller.service;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.CreateTeam;
import org.sample.model.Team;

public interface TeamService {
	public CreateTeam createTeam(CreateTeam creatTeam) throws InvalidUserException;
	public Iterable<Team> getTeams(); 
}
