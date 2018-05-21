package ke.co.dataintegrated.newsfeed.data.databases;

public class NewsFeedDBSchema {
    public static final class UserTable {
         public static final String NAME = "users_tbl";

         public static final class Cols {
             public static final String UUID = "uuid";
             public static final String FNAME = "fname";
             public static final String USERNAME = "username";
             public static final String PASSWORD = "passwd";
             public static final String DATE = "crete_timestamp";
         }
    }
}
