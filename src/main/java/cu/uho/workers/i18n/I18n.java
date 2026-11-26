package cu.uho.workers.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Utility class that handles all the internationalization methods for
 * externalizing strings out of source file.
 * I18n
 */
public final class I18n {

	public static final Locale ENGLISH = Locale.ENGLISH;
	public static final Locale SPANISH = Locale.of("es");
	private static final String BASE_NAME = "cu.uho.workers.i18n.messages";
	private static Locale locale = SPANISH;
	private static ResourceBundle bundle = ResourceBundle.getBundle(BASE_NAME, locale);

	// Suppresses default constructor, ensuring non-instantiability.
	private I18n() {
	}

	/**
	 * Change internationalization locale and reset resource bundle according to the
	 * new locale.
	 * 
	 * @param locale
	 *                   to be set
	 */
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

	/**
	 * Get string value from the key-value pairs of the bundle.
	 * 
	 * @param key
	 *                key for accessing a value in the assigned
	 *                messages.properties.
	 * @return the value that matches the key param.
	 */
	public static String get(String key) {
		return bundle.getString(key);
	}

	/**
	 * Method for especific key-value pairs in the bundle that consists in values
	 * with formatting involved.
	 * 
	 * @param key
	 *                 the key for accessing a value in the assigned
	 *                 messages.properties.
	 * @param args
	 *                 the objects to be formatted.
	 * @return
	 */
	public static String format(String key, Object... args) {
		return new MessageFormat(get(key), locale).format(args);
	}

	/**
	 * 
	 */
	public static String label(Enum<?> enumValue) {
		return get("enum." + enumValue.getDeclaringClass().getSimpleName() + "." + enumValue.name());
	}

	public static String label(Enum<?> enumValue, int idLength) {
		return format("enum.ValidationError.INVALID_ID_FORMAT", idLength);
	}

}
