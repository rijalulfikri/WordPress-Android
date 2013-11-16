package org.wordpress.android.util;

import com.simperium.Simperium;
import com.simperium.client.User;
import com.simperium.client.Bucket;
import com.simperium.client.BucketObject;
import com.simperium.client.BucketNameInvalid;

import org.wordpress.android.Config;
import org.wordpress.android.models.Note;

import android.content.Context;

public class SimperiumUtils {

    private static String TOKEN_FORMAT="WPCC/%s/%s";

    public static Simperium configureSimperium(Context context, String token) {

        Simperium simperium = Simperium.newClient(Config.SIMPERIUM_APP_NAME,
            Config.SIMPERIUM_APP_SECRET, context);

        if (token != null) {
            authorizeUser(simperium, token);
        }

        try {
            Bucket<Note> notesBucket = simperium.bucket(new Note.Schema());
            Bucket<BucketObject> metaBucket = simperium.bucket("meta");

            notesBucket.start();
            metaBucket.start();

        } catch (BucketNameInvalid e) {
            throw new RuntimeException("Failed to configure simperium", e);
        }

        return simperium;
    }

    public static void authorizeUser(Simperium simperium, String token){
        // for now we're going to hardcod mdawaffe's

        User user = simperium.getUser();

        // The token is WPCC/SIMPERIUM_SECRET/WP_OAUTH_ACCESS_TOKEN

        String wpccToken = String.format(TOKEN_FORMAT, Config.SIMPERIUM_APP_SECRET, token);

        user.setAccessToken(wpccToken);

        user.setStatus(User.Status.AUTHORIZED);

    }

}