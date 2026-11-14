package com.kleinkauf.product.util;

import java.util.regex.Pattern;

/*
Turns what the administrator types as the store's WhatsApp into the number the storefront puts in the chat link
(https://wa.me/<number>): international format, digits only, with the country code and without the leading "+".
- Spaces, dashes, dots and parentheses are ignored: "+54 9 (11) 1234-5678" -> "5491112345678".
- A wa.me link is accepted too ("https://wa.me/5491112345678").
Returns null if it is not a number of 8 to 15 digits (the E.164 maximum).
 */
public final class WhatsappNumber {

    private static final Pattern SEPARATORS = Pattern.compile("[\\s.()-]");
    private static final Pattern DIGITS = Pattern.compile("^[1-9][0-9]{7,14}$");
    private static final Pattern WA_LINK = Pattern.compile("(?i)^(https?://)?(www\\.)?wa\\.me/");

    private WhatsappNumber() {
    }

    public static String normalize(String value) {
        String clean = value == null ? "" : value.trim();
        clean = WA_LINK.matcher(clean).replaceFirst("");
        clean = SEPARATORS.matcher(clean).replaceAll("");
        if (clean.startsWith("+")) {
            clean = clean.substring(1);
        }
        return DIGITS.matcher(clean).matches() ? clean : null;
    }
}
