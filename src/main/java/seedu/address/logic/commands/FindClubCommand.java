package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.club.ClubContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindClubCommand extends Command {

    public static final String COMMAND_WORD = "find_club";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all clubs whose fields contains any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…\u200B\n"
            + "Example: " + COMMAND_WORD
            + "n/Basketball p/98765432 e/johnd@example.com a/John street, block 123, #01-01";

    private final ClubContainsKeywordsPredicate predicate;

    public FindClubCommand(ClubContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClubList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredClubList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindClubCommand)) {
            return false;
        }

        FindClubCommand otherFindCommand = (FindClubCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
