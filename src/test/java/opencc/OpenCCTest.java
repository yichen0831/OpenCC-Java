package opencc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OpenCCTest {

    OpenCC openCC;

    @Before
    public void setUp() {
        openCC = new OpenCC();
    }

    @After
    public void tearDown() {
        openCC = null;
    }

    @Test
    public void testHK2S() {
        openCC.setConversion("hk2s");
        String toConvert = "香煙（英語：Cigarette），為煙草製品的一種。滑鼠是一種很常見及常用的電腦輸入設備。";
        String expected = "香烟（英语：Cigarette），为烟草制品的一种。滑鼠是一种很常见及常用的电脑输入设备。";
        assertEquals(expected, openCC.convert(toConvert));
    }

    @Test
    public void testS2HK() {
        openCC.setConversion("s2hk");
        String toConvert = "香烟（英语：Cigarette），为烟草制品的一种。鼠标是一种很常见及常用的电脑输入设备。";
        String expected = "香煙（英語：Cigarette），為煙草製品的一種。鼠標是一種很常見及常用的電腦輸入設備。";
        assertEquals(expected, openCC.convert(toConvert));
    }

    @Test
    public void testS2T() {
        // use default config
        String toConvert = "香烟（英语：Cigarette），为烟草制品的一种。鼠标是一种很常见及常用的电脑输入设备。";
        String expected = "香菸（英語：Cigarette），爲菸草製品的一種。鼠標是一種很常見及常用的電腦輸入設備。";
        assertEquals(expected, openCC.convert(toConvert));
    }

    @Test
    public void testS2TW() {
        openCC.setConversion("s2tw");
        String toConvert = "香烟（英语：Cigarette），为烟草制品的一种。鼠标是一种很常见及常用的电脑输入设备。";
        String expected = "香菸（英語：Cigarette），為菸草製品的一種。鼠標是一種很常見及常用的電腦輸入設備。";
        assertEquals(expected, openCC.convert(toConvert));
    }

    @Test
    public void testS2TWP() {
        openCC.setConversion("s2twp");
        String toConvert = "香烟（英语：Cigarette），为烟草制品的一种。內存是一种很常见及常用的电脑输入设备。";
        String expected = "香菸（英語：Cigarette），為菸草製品的一種。記憶體是一種很常見及常用的電腦輸入裝置。";
        assertEquals(expected, openCC.convert(toConvert));
    }

    @Test
    public void testT2HK() {
        openCC.setConversion("t2hk");
        String toConvert = "香菸（英語：Cigarette），爲菸草製品的一種。滑鼠是一種很常見及常用的電腦輸入裝置。";
        String expected = "香煙（英語：Cigarette），為煙草製品的一種。滑鼠是一種很常見及常用的電腦輸入裝置。";
        assertEquals(expected, openCC.convert(toConvert));
    }

    @Test
    public void testT2S() {
        openCC.setConversion("t2s");
        String toConvert = "香菸（英語：Cigarette），爲菸草製品的一種。滑鼠是一種很常見及常用的電腦輸入裝置。";
        String expected = "香烟（英语：Cigarette），为烟草制品的一种。滑鼠是一种很常见及常用的电脑输入装置。";
        assertEquals(expected, openCC.convert(toConvert));
    }

    @Test
    public void testT2TW() {
        openCC.setConversion("t2tw");
        String toConvert = "香菸（英語：Cigarette），爲菸草製品的一種。滑鼠是一種很常見及常用的電腦輸入設備。";
        String expected = "香菸（英語：Cigarette），為菸草製品的一種。滑鼠是一種很常見及常用的電腦輸入設備。";
        assertEquals(expected, openCC.convert(toConvert));
    }

    @Test
    public void testTW2S() {
        openCC.setConversion("tw2s");
        String toConvert = "香菸（英語：Cigarette），為菸草製品的一種。滑鼠是一種很常見及常用的電腦輸入裝置。";
        String expected = "香烟（英语：Cigarette），为烟草制品的一种。滑鼠是一种很常见及常用的电脑输入装置。";
        assertEquals(expected, openCC.convert(toConvert));
    }

    @Test
    public void testTW2SP() {
        openCC.setConversion("tw2sp");
        String toConvert = "香菸（英語：Cigarette），為菸草製品的一種。記憶體是一種很常見及常用的電腦輸入裝置。";
        String expected = "香烟（英语：Cigarette），为烟草制品的一种。内存是一种很常见及常用的电脑输入设备。";
        assertEquals(expected, openCC.convert(toConvert));
    }

}
