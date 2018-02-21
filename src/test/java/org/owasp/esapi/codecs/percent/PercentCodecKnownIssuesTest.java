package org.owasp.esapi.codecs.percent;
import static org.owasp.esapi.codecs.percent.PercentCodecStringTest.PERCENT_CODEC_IMMUNE;

import org.junit.Assert;
import org.junit.Test;
import org.owasp.esapi.codecs.PercentCodec;
/**
 * This test class holds the proof of known deficiencies, inconsistencies, or bugs with the PercentCodec implementation.
 * <br/>
 * The intent is that when that functionality is corrected, these tests should break. That should hopefully encourage
 * the author to move the test to an appropriate Test file and update the functionality to a working expectation.
 */
public class PercentCodecKnownIssuesTest {
    
    private PercentCodec codec = new PercentCodec();

    /**
     * PercentCodec has not been fully implemented for codepoint support, which handles UTF16 characters (based on my current understanding).
     * As such, the encoding/decoding of UTF16 will not function as desired through the codec implementation.
     * <br/>
     * When the functionality is corrected this test will break. At that point UTF16 tests should be added to {@link PercentCodecStringTest} and {@link PercentCodecCharacterTest}.
     */
    @Test
    public void failsUTF16Conversions() {
        //This should be 195
        int incorrectDecodeExpect = 196;
        
        char[] encodeImmune = PERCENT_CODEC_IMMUNE;
        String decodedValue = ""+(char) 0x100;
        String input = "%C4%80";

        String actualDecodeChar = codec.decode(input);
        int actualChar = (int)actualDecodeChar.charAt(0);
        
        Assert.assertEquals(incorrectDecodeExpect, actualChar);
        
        //This works as expected.
        Assert.assertEquals(input, codec.encode(encodeImmune, decodedValue));
    }
    
}
