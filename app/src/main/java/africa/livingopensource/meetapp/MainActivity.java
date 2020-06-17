package africa.livingopensource.meetapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.jitsi.meet.sdk.JitsiMeetUserInfo;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialise server URL
        URL serverURL;
        try {
            serverURL = new URL("https://meet.jit.si");
        }
        catch (MalformedURLException err) {
            err.printStackTrace();
            throw new RuntimeException("Invalid server URL");
        }

        JitsiMeetUserInfo userInfo
                = new JitsiMeetUserInfo();
        userInfo.setDisplayName("LOSF Member");

        JitsiMeetConferenceOptions defaultOptions
                = new JitsiMeetConferenceOptions.Builder()
                .setServerURL(serverURL)
                .setVideoMuted(true)
                .setAudioMuted(true)
                .setWelcomePageEnabled(false)
                .setUserInfo(userInfo)
                .build();

        JitsiMeet.setDefaultConferenceOptions(defaultOptions);
    }

    public void onButtonClick(View v) {
        EditText name = findViewById(R.id.name);
        EditText conference = findViewById(R.id.conference);

        JitsiMeetUserInfo userInfo
                = new JitsiMeetUserInfo();
        userInfo.setDisplayName(name.getText().toString());

        if ( name.getText().toString().length() > 0 &&
                conference.getText().toString().length() > 9) {
            JitsiMeetConferenceOptions opts
                    = new JitsiMeetConferenceOptions.Builder()
                    .setRoom(conference.getText().toString())
                    .setUserInfo(userInfo)
                    .build();

            // Start the conference
            JitsiMeetActivity.launch(this, opts);
        }
    }

}