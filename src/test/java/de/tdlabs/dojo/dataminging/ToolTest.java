package de.tdlabs.dojo.dataminging;


import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tom on 07.03.16.
 */
public class ToolTest {

    private final static String INPUT_DATA_POINTS_WITH_HEADER_1_ENTRY = //
            "  Dy MxT   MnT   AvT   HDDay  AvDP 1HrP TPcpn WxType PDir AvSp Dir MxS SkyC MxR MnR AvSLP\n" +
                    "   1  88    59    74          53.8       0.00 F       280  9.6 270  17  1.6  93 23 1004.5";

    private final static String INPUT_DATA_LINE_1 = "   1  88    59    74          53.8       0.00 F       280  9.6 270  17  1.6  93 23 1004.5";

    private final static String INPUT_DATA_LINE_2_MALFORMED_MIN_T = "9  86    32*   59       6  61.5       0.00         240  7.6 220  12  6.0  78 46 1018.6";

    private final static String INPUT_DATA_LINE_3_MALFORMED_MAX_T = "26  97*   64    81          70.4       0.00 H       050  5.1 200  12  4.0 107 45 1014.9";

    private final static String INPUT_DATA_LINE_4_MALFORMED_DAY = "mo  82.9  60.5  71.7    16  58.8       0.00              6.9          5.3";

    private Tool tool;

    @Before
    public void setup(){
        tool = new Tool();
    }

    @Test
    public void testCreateToolInstance() {
        new Tool();
    }

    @Test
    public void testParseDataPoints() {

        List<TempDataRecord> dataRecords = tool.parse(INPUT_DATA_POINTS_WITH_HEADER_1_ENTRY);

        assertThat(dataRecords).hasSize(1);

        TempDataRecord tempDataRecord = dataRecords.get(0);

        assertThat(tempDataRecord.getDay()).isEqualTo(1);
        assertThat(tempDataRecord.getTempMin()).isEqualTo(59);
        assertThat(tempDataRecord.getTempMax()).isEqualTo(88);
    }

    @Test
    public void testParseDataLine() {

        TempDataRecord tempDataRecord = tool.parseDataLine(INPUT_DATA_LINE_1);

        assertThat(tempDataRecord.getDay()).isEqualTo(1);
        assertThat(tempDataRecord.getTempMin()).isEqualTo(59);
        assertThat(tempDataRecord.getTempMax()).isEqualTo(88);
    }

    @Test
    public void testParseDataLineMalformedMinT() {

        TempDataRecord tempDataRecord = tool.parseDataLine(INPUT_DATA_LINE_2_MALFORMED_MIN_T);

        assertThat(tempDataRecord.getDay()).isEqualTo(1);
        assertThat(tempDataRecord.getTempMin()).isEqualTo(59);
        assertThat(tempDataRecord.getTempMax()).isEqualTo(88);
    }

    @Test
    public void testParseDataLineMalformedMaxT() {

        TempDataRecord tempDataRecord = tool.parseDataLine(INPUT_DATA_LINE_3_MALFORMED_MAX_T);

        assertThat(tempDataRecord.getDay()).isEqualTo(1);
        assertThat(tempDataRecord.getTempMin()).isEqualTo(59);
        assertThat(tempDataRecord.getTempMax()).isEqualTo(88);
    }

    @Test
    public void testParseDataLineMalformedDay() {

        TempDataRecord tempDataRecord = tool.parseDataLine(INPUT_DATA_LINE_4_MALFORMED_DAY);

        assertThat(tempDataRecord.getDay()).isEqualTo(1);
        assertThat(tempDataRecord.getTempMin()).isEqualTo(59);
        assertThat(tempDataRecord.getTempMax()).isEqualTo(88);
    }
}