package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.help.DetailHelpCommand;
import seedu.address.logic.commands.help.HelpDescription;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class DetailHelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_addHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpDescription.ADD_COMMAND_DESCRIPTION,
                null, true, false);
        assertCommandSuccess(new DetailHelpCommand("add"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_deleteHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpDescription.DELETE_COMMAND_DESCRIPTION,
                null, true, false);
        assertCommandSuccess(new DetailHelpCommand("delete"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_editHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpDescription.EDIT_COMMAND_DESCRIPTION,
                null, true, false);
        assertCommandSuccess(new DetailHelpCommand("edit"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpDescription.LIST_COMMAND_DESCRIPTION,
                null, true, false);
        assertCommandSuccess(new DetailHelpCommand("list"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_exitHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpDescription.EXIT_COMMAND_DESCRIPTION,
                null, true, false);
        assertCommandSuccess(new DetailHelpCommand("exit"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidHelpCommand_throwsCommandException() {
        assertCommandFailure(new DetailHelpCommand("wrong command somehow"), model,
                HelpDescription.COMMAND_NOT_FOUND_DESCRIPTION);
    }
}
