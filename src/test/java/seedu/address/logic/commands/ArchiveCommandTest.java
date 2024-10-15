package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.util.FileUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ArchiveCommandTest {
    @BeforeEach
    public void setUp() throws IOException {
        FileUtil.createIfMissing(new UserPrefs().getAddressBookFilePath());
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(new UserPrefs().getAddressBookFilePath());
    }

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ArchiveCommand(), model, ArchiveCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(new ArchiveCommand(), model, ArchiveCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_emptyAddressBook_failure() {
        Model model = new ModelManagerStubThrowingIoException();
        Model expectedModel = new ModelManagerStubThrowingIoException();

        assertCommandSuccess(new ArchiveCommand(), model, ArchiveCommand.MESSAGE_FAILURE, expectedModel);
    }

    /**
     * A ModelManager stub that always throws IOException when archiveAddressBook() is called.
     */
    private static class ModelManagerStubThrowingIoException extends ModelManager {
        @Override
        public void archiveAddressBook() throws IOException {
            throw new IOException();
        }
    }
}
