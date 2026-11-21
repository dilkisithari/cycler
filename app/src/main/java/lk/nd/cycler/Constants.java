package lk.nd.cycler;

/**
 * App-wide constants.
 *
 * Secrets are NOT stored here. They are read from local.properties at build time
 * (see local.properties) and exposed through BuildConfig.
 */
public final class Constants {

    private Constants() {}

    /** PayHere merchant id (public identifier, loaded from local.properties). */
    public static final String MERCHANT_ID = BuildConfig.PAYHERE_MERCHANT_ID;
}
