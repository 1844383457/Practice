package net.lzzy.practice.utils;

import junit.framework.TestCase;

/**
 * Created by Administrator on 2016/11/8.
 */
public class AppUtilsTest extends TestCase {
    public void testIsConnectivity() throws Exception {
        assertEquals(false, AppUtils.isConnectivity());
    }

    public void testUpdateNetState() throws Exception {
        AppUtils.updateNetState();
        assertEquals(true, AppUtils.isLocal);
    }

}