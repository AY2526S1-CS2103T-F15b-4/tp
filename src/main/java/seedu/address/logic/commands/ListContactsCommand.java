package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLUBS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListContactsCommand extends Command {

    public static final String COMMAND_WORD = "list_contacts";

    public static final String MESSAGE_SUCCESS = "Listed all contacts (clubs & persons)";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredClubList(PREDICATE_SHOW_ALL_CLUBS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
