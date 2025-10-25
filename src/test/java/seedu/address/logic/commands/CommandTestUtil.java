package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.club.Club;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditClubDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String VALID_NAME_ART = "Art Club";
    public static final String VALID_NAME_BALL = "Ball Club";
    public static final String VALID_NAME_BOOKS = "Book Club";
    public static final String VALID_PHONE_ART = "11111111";
    public static final String VALID_PHONE_BALL = "12345678";
    public static final String VALID_PHONE_BOOKS = "22222222";
    public static final String VALID_EMAIL_ART = "art@example.com";
    public static final String VALID_EMAIL_BOOKS = "book@example.com";
    public static final String VALID_ADDRESS_ART = "Block 312, Art Street 1";
    public static final String VALID_ADDRESS_BOOKS = "Block 123, Book Street 3";
    public static final String VALID_TAG_BIG = "big";
    public static final String VALID_TAG_CASUAL = "casual";

    public static final String NAME_DESC_ART = " " + PREFIX_NAME + VALID_NAME_ART;
    public static final String NAME_DESC_BOOKS = " " + PREFIX_NAME + VALID_NAME_BOOKS;
    public static final String PHONE_DESC_ART = " " + PREFIX_PHONE + VALID_PHONE_ART;
    public static final String PHONE_DESC_BOOKS = " " + PREFIX_PHONE + VALID_PHONE_BOOKS;
    public static final String EMAIL_DESC_ART = " " + PREFIX_EMAIL + VALID_EMAIL_ART;
    public static final String EMAIL_DESC_BOOKS = " " + PREFIX_EMAIL + VALID_EMAIL_BOOKS;
    public static final String ADDRESS_DESC_ART = " " + PREFIX_ADDRESS + VALID_ADDRESS_ART;
    public static final String ADDRESS_DESC_BOOKS = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOOKS;
    public static final String TAG_DESC_BIG = " " + PREFIX_TAG + VALID_TAG_BIG;
    public static final String TAG_DESC_CASUAL = " " + PREFIX_TAG + VALID_TAG_CASUAL;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James!"; // '!' not allowed by Name regex
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = "" +
            "" +
            " " + PREFIX_ADDRESS + "Blk 123 ! Road"; // '!' not allowed
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final int VALID_MEMBER_INDEX_1 = 1;
    public static final int VALID_MEMBER_INDEX_2 = 2;
    public static final int VALID_CLUB_INDEX_1 = 3;
    public static final int VALID_CLUB_INDEX_2 = 4;
    public static final int VALID_DURATION = 10;

    public static final String VALID_MEMBER_INDEX_DESC = " " + PREFIX_MEMBER
            + VALID_MEMBER_INDEX_1 + " " + VALID_MEMBER_INDEX_2;
    public static final String VALID_CLUB_INDEX_DESC = " " + PREFIX_CLUB
            + VALID_CLUB_INDEX_1 + " " + VALID_CLUB_INDEX_2;;
    public static final String VALID_DURATION_DESC = " " + PREFIX_DURATION + VALID_DURATION;
    public static final String INVALID_DURATION_DESC = " " + PREFIX_DURATION + "abc";
    public static final String INVALID_MEMBER_INDEX_DESC = " " + PREFIX_MEMBER + "1 a";
    public static final String INVALID_CLUB_INDEX_DESC = " " + PREFIX_CLUB + "b 2";

    public static final EditPersonCommand.EditPersonDescriptor DESC_AMY;
    public static final EditPersonCommand.EditPersonDescriptor DESC_BOB;

    public static final EditClubCommand.EditClubDescriptor DESC_ART;
    public static final EditClubCommand.EditClubDescriptor DESC_BALL;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

        DESC_ART = new EditClubDescriptorBuilder().withName(VALID_NAME_ART)
                .withPhone(VALID_PHONE_ART).withEmail(VALID_EMAIL_ART).withAddress(VALID_ADDRESS_ART)
                .withTags(VALID_TAG_CASUAL).build();

        DESC_BALL = new EditClubDescriptorBuilder().withName(VALID_NAME_BOOKS)
                .withPhone(VALID_PHONE_BOOKS).withEmail(VALID_EMAIL_BOOKS).withAddress(VALID_ADDRESS_BOOKS)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person target = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        model.updateFilteredPersonList(p -> p.equals(target));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the club at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showClubAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredClubList().size());

        Club club = model.getFilteredClubList().get(targetIndex.getZeroBased());
        model.updateFilteredClubList(otherClub -> otherClub.equals(club));

        assertEquals(1, model.getFilteredClubList().size());
    }

}
