public interface UserStorage {
    void add(String login, String password);

    boolean exist(String login);

    boolean passwordTooShort(String password);
}
