package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.club.Club;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Deletes a club identified using it's displayed index from the address book.
 */
public class DeleteClubCommand extends Command {

    public static final String COMMAND_WORD = "delete_club";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the club identified by the index number used in the displayed club list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CLUB_SUCCESS = "Deleted Club: %1$s";

    private final Index targetIndex;

    public DeleteClubCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Club> lastShownList = model.getFilteredClubList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLUB_DISPLAYED_INDEX);
        }

        Club clubToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteClub(clubToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_CLUB_SUCCESS, Messages.format(clubToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteClubCommand)) {
            return false;
        }

        DeleteClubCommand otherDeleteCommand = (DeleteClubCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
