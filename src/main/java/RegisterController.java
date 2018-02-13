public class RegisterController {
    private UserStorage userStorage;

    public RegisterController(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public Response addNewUser(String login, String password) {

        Response response = new Response();
        if (userStorage.exist(login)) {
            response.setSuccess(false);
            response.setMassage("Login busy , try other");
        } else if (userStorage.passwordTooShort(password)) {
            response.setSuccess(false);
            response.setMassage("Password too short");
        } else {
            response.setSuccess(true);
            response.setMassage("operation successful");
            userStorage.add(login, password);
        }
        return response;
    }
}
