package seedu.address.logic.search.parsers;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.search.predicates.NameMatchesPredicate;
import seedu.address.model.field.Searchable;

/**
 * Parses input arguments for a search by name instruction
 */
public class NameParser implements SearchParser<Searchable> {

    public static final String KEYWORD = "n";

    /**
     * Parses the given {@code String} of arguments in the context of a search by name
     * instruction and returns a corresponding {@code Predicate<Searchable>} object.
     * @throws ParseException if the user input does not conform the expected format
     */
    public Predicate<Searchable> parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");
        return new NameMatchesPredicate(Arrays.asList(nameKeywords));
    }

}
