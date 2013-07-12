/**
 * Project: com.alibaba.testme.remote.http
 * 
 * File Created at 2013-7-11
 * $Id$
 * 
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.alibaba.testme.remote.http.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.util.FileCopyUtils;

import com.alibaba.testme.remote.http.HttpClient;

/**
 * TODO Comment of HttpClientImpl
 * 
 * @author lz
 */
public class HttpClientImpl implements HttpClient {

    @Override
    public String sendHttpPost(String destUrl, Map<String, String> httpParams)
            throws MalformedURLException, IOException {
        final URL url = new URL(destUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(3000);
        connection.setDoOutput(true);

        PrintWriter out = new PrintWriter(connection.getOutputStream());
        boolean isFirst = true;
        for (Map.Entry<String, String> entry : httpParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (null == value) {
                continue;
            }

            if (isFirst) {
                isFirst = false;
            } else {
                out.print("&");
            }

            out.print(URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8"));
        }
        out.close();

        return toString(connection.getInputStream(), "UTF-8");
    }

    public String toString(InputStream input, String encoding) throws IOException {
        StringWriter sw = new StringWriter();
        copy(input, sw, encoding);
        return sw.toString();
    }

    public void copy(InputStream input, Writer output, String encoding) throws IOException {
        if (encoding == null) {
            FileCopyUtils.copy(new InputStreamReader(input), output);

        } else {
            InputStreamReader in = new InputStreamReader(input, encoding);
            FileCopyUtils.copy(in, output);
        }
    }
}
