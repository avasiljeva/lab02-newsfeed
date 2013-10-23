package lv.lu.newsfeed.model.jpa;

/**
 * Put names for named JPQL queries here
 */
public final class JPQConst {	

	public final class UserJpq {
	    public static final String QUERY_GET_ID_BY_USERNAME = "User.getIdByUsername";
		public static final String QUERY_GET_USER_BY_USERNAME = "User.getUserByUsername";
		public static final String QUERY_GET_ALL_EXCLUDE_USERNAME = "User.getAllExcludeUsername";
	}
	
	private JPQConst() {}
}
