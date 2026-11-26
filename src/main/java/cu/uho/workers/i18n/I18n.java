package cu.uho.workers.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class I18n {

	public static final Locale ENGLISH = Locale.ENGLISH;
	public static final Locale SPANISH = Locale.of("es");

	private static final String BASE_NAME = "cu.uho.workers.i18n.messages";
	private static Locale locale = SPANISH;
	private static ResourceBundle bundle = ResourceBundle.getBundle(BASE_NAME, locale);

	private I18n() {
	}

	public static void setLocale(Locale locale) {
		I18n.locale = locale;
		bundle = ResourceBundle.getBundle(BASE_NAME, locale);
	}

	public static Locale getLocale() {
		return locale;
	}

	public static ResourceBundle getBundle() {
		return bundle;
	}

	public static String get(String key) {
		return bundle.getString(key);
	}

	public static String format(String key, Object... args) {
		return new MessageFormat(get(key), locale).format(args);
	}

}
