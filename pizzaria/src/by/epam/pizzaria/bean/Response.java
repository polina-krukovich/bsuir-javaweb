package by.epam.pizzaria.bean;

import by.epam.pizzaria.controller.command.CommandStatus;

/**
 * Base Response class used for transporting response between layers. <br>
 *
 * @author Polina Krukovich
 */

public class Response {
    /**
     * Error message is set if command fails
     */
    protected String errorMessage;
    /**
     * Success message is set if command succeeds
     */
    protected String successMessage;
    /**
     * Indicates if command succeed or failed
     */
    protected CommandStatus status;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public CommandStatus getStatus() {
        return status;
    }

    public void setStatus(CommandStatus status) {
        this.status = status;
    }
}
