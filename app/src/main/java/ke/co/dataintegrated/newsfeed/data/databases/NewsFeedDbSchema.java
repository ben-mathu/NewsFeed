package ke.co.dataintegrated.newsfeed.data.databases;

public class NewsFeedDbSchema {
    public static final class UserTable {
        public static final String NAME =  "users_tbl";
        public static final class Cols {
            public static final String USER_UUID = "uuid";
            public static final String FNAME = "fname";
            public static final String USERNAME = "username";
            public static final String EMAIL = "email";
            public static final String PASSWORD = "passwd";
            public static final String TIMESTAMP = "create_timestamp";
        }

        public static final String CREATE_USERS_TBL = "CREATE TABLE " + NAME +
                "(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                Cols.USER_UUID + " INTEGER NOT NULL, " +
                Cols.FNAME + " TEXT NOT NULL," +
                Cols.USERNAME + " TEXT NOT NULL," +
                Cols.EMAIL + " TEXT NOT NULL," +
                Cols.PASSWORD + " TEXT NOT NULL," +
                Cols.TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";
    }

    public static final class ArticlesTable {
        public static final String NAME =  "articles_tbl";
        public static final class Cols {
            public static final String ARTICLE_UUID = "uuid";
            public static final String SOURCE_NAME = "fname";
            public static final String TITLE = "username";
            public static final String DESCRIPTION = "email";
            public static final String URL = "passwd";
            public static final String TIMESTAMP = "create_timestamp";
        }

        public static final String CREATE_ARTICLES_TBL = "CREATE TABLE " + NAME +
                "(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                Cols.ARTICLE_UUID + " INTEGER NOT NULL, " +
                Cols.SOURCE_NAME + " TEXT NOT NULL," +
                Cols.TITLE + " TEXT NOT NULL," +
                Cols.DESCRIPTION + " TEXT NOT NULL," +
                Cols.URL + " TEXT NOT NULL," +
                Cols.TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";
    }
}
