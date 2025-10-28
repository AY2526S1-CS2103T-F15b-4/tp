package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.club.Club;
import seedu.address.model.person.Person;

/**
 * Cancels a Person's membership in a Club
 */
public class CancelMembershipCommand extends Command {

    public static final String COMMAND_WORD = "cancel";
    public static final String MESSAGE_CANCELLED_MEMBERSHIP =
            "%1$s membership in %2$s cancelled. Membership only remains valid till expiry date.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Cancel a person's membership in a club"
            + "Current membership is still valid until expiry date.\n"
            + "Cancelled membership cannot be renewed but can be reactivated.\n"
            + "Parameters: "
            + PREFIX_MEMBER + "PERSON_INDEX "
            + PREFIX_CLUB + "CLUB_INDEX "
            + "Example: " + COMMAND_WORD + " m/1 c/2";

    private final Index personIndex;
    private final Index clubIndex;

    /**
     * @param personIndex of the person in the filtered person list to edit
     * @param clubIndex of the club in the filtered club list to edit
     */
    public CancelMembershipCommand(Index personIndex, Index clubIndex) {
        requireAllNonNull(personIndex, clubIndex);
        this.personIndex = personIndex;
        this.clubIndex = clubIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Club> lastShownClubList = model.getFilteredClubList();

        if (personIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        if (clubIndex.getZeroBased() >= lastShownClubList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLUB_DISPLAYED_INDEX);
        }
        Person personToCancel = lastShownPersonList.get(personIndex.getZeroBased());
        Club clubToCancelIn = lastShownClubList.get(clubIndex.getZeroBased());

        try {
            model.cancelMembership(personToCancel, clubToCancelIn);
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }
        return new CommandResult(String.format(
                MESSAGE_CANCELLED_MEMBERSHIP,
                personToCancel.getName(),
                clubToCancelIn.getName()
        ));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CancelMembershipCommand)) {
            return false;
        }

        CancelMembershipCommand otherCancelCommand = (CancelMembershipCommand) other;
        return personIndex.equals(otherCancelCommand.personIndex)
                && clubIndex.equals(otherCancelCommand.clubIndex);
    }
}
