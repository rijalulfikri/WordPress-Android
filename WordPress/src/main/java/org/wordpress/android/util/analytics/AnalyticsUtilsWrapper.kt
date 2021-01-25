package org.wordpress.android.util.analytics

import android.content.Context
import android.net.Uri
import dagger.Reusable
import javax.inject.Inject

/**
 * Injectable wrapper around AnalyticsUtils.
 *
 * AnalyticsUtils interface is consisted of static methods, which make the client code difficult to test/mock.
 * Main purpose of this wrapper is to make testing easier.
 *
 */
@Reusable
class AnalyticsUtilsWrapper @Inject constructor(private val appContext: Context) {
    fun getMediaProperties(isVideo: Boolean, mediaURI: Uri?, path: String?): MutableMap<String, Any?> =
            AnalyticsUtils.getMediaProperties(appContext, isVideo, mediaURI, path)
}
