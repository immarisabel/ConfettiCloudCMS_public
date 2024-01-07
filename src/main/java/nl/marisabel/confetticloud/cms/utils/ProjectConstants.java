package nl.marisabel.confetticloud.cms.utils;

import java.util.Locale;


public final class ProjectConstants {



	public static final String DEFAULT_ENCODING = "UTF-8";

	public static final Locale AMSTERDAM_LOCALE = new Locale.Builder().setLanguage("en").setRegion("NL").build();

	private ProjectConstants() {

		throw new UnsupportedOperationException();
	}

}
