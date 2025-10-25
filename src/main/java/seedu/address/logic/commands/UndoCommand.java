package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLUBS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.parser.CommandList;
import seedu.address.model.Model;

/**
 * Undoes the last executed command.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    private String lastCommand;
    private String messageSuccess;

    /**
     * Constructs an UndoCommand that will undo the last command issued.
     */
    public UndoCommand() {
        this.lastCommand = CommandList.getLastCommand();
        this.messageSuccess = "Undid the command: " + lastCommand;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        final String commandWord = lastCommand.trim().split("\\s+")[0];

        switch (commandWord) {
        case AddClubCommand.COMMAND_WORD:
            model.removeLastAddedClub();
            break;

        case AddPersonCommand.COMMAND_WORD:
            model.removeLastAddedPerson();
            break;

        case ClearCommand.COMMAND_WORD:
            model.restoreAddressBook();
            break;

        case DeleteClubCommand.COMMAND_WORD:
            model.restoreClub();
            break;

        case DeletePersonCommand.COMMAND_WORD:
            model.restorePerson();
            break;

        case EditPersonCommand.COMMAND_WORD:
            model.revertEditedPerson();
            break;

        case EditClubCommand.COMMAND_WORD:
            model.revertEditedClub();
            break;

        case FindClubCommand.COMMAND_WORD, FilterClubCommand.COMMAND_WORD:
            model.updateFilteredClubList(PREDICATE_SHOW_ALL_CLUBS);
            break;

        case FindPersonCommand.COMMAND_WORD, FilterPersonCommand.COMMAND_WORD:
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            break;

        case ListMemberCommand.COMMAND_WORD, ListMembershipCommand.COMMAND_WORD:
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            model.updateFilteredClubList(PREDICATE_SHOW_ALL_CLUBS);
            break;

        default:
            return new CommandResult("");
        }

        return new CommandResult(messageSuccess);
    }
}
