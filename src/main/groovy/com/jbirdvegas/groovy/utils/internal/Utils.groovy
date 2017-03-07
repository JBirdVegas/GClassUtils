package com.jbirdvegas.groovy.utils.internal

import java.nio.charset.StandardCharsets

class Utils {
    static class Http {
        static post(String url, String body, Map<String, Object> headers) {
            doWithOutput("POST", url, body, headers)
        }

        static patch(String url, String body, Map<String, Object> headers) {
            doWithOutput("PATCH", url, body, headers)
        }

        static put(String url, String body, Map<String, Object> headers) {
            doWithOutput("PUT", url, body, headers)
        }

        private static doWithOutput(String method, String url, String body, Map<String, Object> headers) {
            HttpURLConnection httpCon = (HttpURLConnection) new URL(url).openConnection()
            httpCon.method = method
            httpCon.setDoOutput(true)
            headers?.each {
                // not a typo we force values to string () before adding
                // to map or we might add a GString... not good.
                httpCon.setRequestProperty((it.key), ("$it.value"))
            }
            httpCon.getOutputStream().withWriter(StandardCharsets.UTF_8.toString()) {
                it.write(body)
            }
            return ((InputStream) httpCon.getContent()).text
        }
    }
}
