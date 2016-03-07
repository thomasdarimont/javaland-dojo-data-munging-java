package de.tdlabs.dojo.dataminging;


import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tom on 07.03.16.
 */
public class ToolTest {

    private final static String INPUT_DATA_POINTS_1_ENTRY = //
            "  Dy MxT   MnT   AvT   HDDay  AvDP 1HrP TPcpn WxType PDir AvSp Dir MxS SkyC MxR MnR AvSLP\n" +
                    "   1  88    59    74          53.8       0.00 F       280  9.6 270  17  1.6  93 23 1004.5";

    @Test
    public void testCreateToolInstance() {
        new Tool();
    }

    @Test
    public void testParseDataPoints() throws Exception {

        Tool tool = new Tool();

        List<TempDataRecord> dataRecords = tool.parse(INPUT_DATA_POINTS_1_ENTRY);

        assertThat(dataRecords).hasSize(1);

        TempDataRecord tempDataRecord = dataRecords.get(0);

        assertThat(tempDataRecord.getDay()).isEqualTo(1);
        assertThat(tempDataRecord.getTempMin()).isEqualTo(59);
        assertThat(tempDataRecord.getTempMax()).isEqualTo(88);
    }
}