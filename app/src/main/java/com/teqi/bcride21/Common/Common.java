package com.teqi.bcride21.Common;

import com.teqi.bcride21.Remote.FCMClient;
import com.teqi.bcride21.Remote.GoogleMapAPI;
import com.teqi.bcride21.Remote.IFCMService;
import com.teqi.bcride21.Remote.IGoogleAPI;

//ep16 0348
public class Common {

    public static final String driver_tbl = "Drivers";
    public static final String user_driver_tbl = "DriversInformation";
    public static final String user_customer_tbl = "CustomerInformation";
    public static final String pickup_request_tbl = "PickupRequest";
    public static final String token_tbl = "Tokens";

    public static final String fcmURL = "https://fcm.googleapis.com/";
    public static final String googleAPIUrl = "https://maps.googleapis.com/";

    private static double base_fare = 60;
    private static double time_rate = 1;
    private static double distance_rate = 10;

    public static double getPrice(double km,int min)
    {
        return (base_fare+(time_rate*min)+(distance_rate*km));
    }



    public static IFCMService getFCMService()
    {
        return FCMClient.getClient(fcmURL).create(IFCMService.class);
    }

    public static IGoogleAPI getGoogleService()
    {
        return GoogleMapAPI.getClient(googleAPIUrl).create(IGoogleAPI.class);//ep16 0821
    }
}
