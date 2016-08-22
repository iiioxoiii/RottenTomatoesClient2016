package org.ecaib.rottentomatoesclient2016;

import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class RottenTomatoesContentProvider extends CupboardContentProvider {
    // The content provider authority is used for building Uri's for the provider
    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    static {
        cupboard().register(Movie.class);
    }

    public RottenTomatoesContentProvider() {
        super(AUTHORITY, 1);
    }
}
