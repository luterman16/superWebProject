package by.mmarshal.app.bootstrap.command;

import by.mmarshal.app.bootstrap.exceptions.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface BaseCommand {
    String execute(HttpServletRequest request) throws CommandException;
}
