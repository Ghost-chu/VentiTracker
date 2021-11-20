/*
 * This file is a part of project QuickShop, the name is HttpUtil.java
 *  Copyright (C) PotatoCraft Studio and contributors
 *
 *  This program is free software: you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License as published by the
 *  Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT
 *  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 *  FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 *  for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.ghostchu.ventitracker;

import lombok.val;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class HttpUtil {
    private static final OkHttpClient client = new OkHttpClient.Builder().build();

    public static HttpUtil create() {
        return new HttpUtil();
    }

    public static Response makeGet(@NotNull String url) throws IOException {
        return client.newCall(new Request.Builder().cacheControl(new CacheControl.Builder().noCache().noStore().build()).get().url(url).build()).execute();
    }

    public static String createGet(@NotNull String url) throws IOException{
        String cache;
        try (Response response = client.newCall(new Request.Builder().cacheControl(new CacheControl.Builder().noCache().noStore().build()).get().url(url).addHeader("cookie","SESSDATA=05574db7%2C1652540843%2C62b1c*b1").build()).execute()) {
            val body = response.body();
            if (body == null) {
                throw new IOException("Body is empty");
            }
            cache = body.string();
            if (response.code() != 200) {
                throw new IOException("Invalid Http Response Code: "+response.code()+": "+ body.string());
            }
            return cache;
        }
    }

    public static Response makePost(@NotNull String url, @NotNull RequestBody body) throws IOException {
        try (Response response = client.newCall(new Request.Builder().post(body).url(url).build()).execute()) {
            // do nothing
            return response;
        }
    }

    public OkHttpClient getClient() {
        return client;
    }
}
