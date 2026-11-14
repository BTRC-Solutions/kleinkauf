package com.kleinkauf.product;

import com.kleinkauf.product.util.WhatsappNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class WhatsappNumberTest {

    @Test
    void ignoresSeparatorsAndThePlus() {
        assertEquals("5491112345678", WhatsappNumber.normalize("+54 9 (11) 1234-5678"));
        assertEquals("5491112345678", WhatsappNumber.normalize(" 549.11.1234.5678 "));
    }

    @Test
    void acceptsWaMeLinks() {
        assertEquals("5491112345678", WhatsappNumber.normalize("https://wa.me/5491112345678"));
        assertEquals("5491112345678", WhatsappNumber.normalize("wa.me/+5491112345678"));
    }

    @Test
    void rejectsWhatIsNotANumber() {
        assertNull(WhatsappNumber.normalize(null));
        assertNull(WhatsappNumber.normalize("   "));
        assertNull(WhatsappNumber.normalize("1234567"), "too short");
        assertNull(WhatsappNumber.normalize("1234567890123456"), "more than 15 digits");
        assertNull(WhatsappNumber.normalize("011 1234 5678"), "without the country code it starts with 0");
        assertNull(WhatsappNumber.normalize("+54 11 abc"));
        assertNull(WhatsappNumber.normalize("https://example.com/5491112345678"));
    }
}
