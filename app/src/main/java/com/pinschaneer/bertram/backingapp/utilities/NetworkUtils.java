package com.pinschaneer.bertram.backingapp.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to  communicate with the themoviedb.org server
 */
public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    /**
     * Builds a Url which can receive data from the backing server server
     *
     * @return the URL for requesting the backing server
     */
    public static URL backingDataUrl() {
        String baseUrl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
        Uri builtUri = Uri.parse(baseUrl).buildUpon()
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;

    }

    /**
     * This method opens a Network connection to the given Url an returns the response of this Url
     *
     * @param url The URL to get Data from
     * @return the response of the Url
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {


        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } catch (EOFException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return null;

    }


}
