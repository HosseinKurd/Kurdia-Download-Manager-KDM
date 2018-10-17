package com.hosseinkurd.downloadsample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.hosseinkurd.kdm.KDM;
import com.hosseinkurd.kdm.OnDownload;

import java.io.File;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnDownload {

    private static final int STORAGE_PERMISSION_RC = 1;
    private TextView textView;
    private Button button;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_RC &&  grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            download();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            if (isStoragePermissionGranted(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                download();
            }
        });
    }

    private void download() {
        button.setEnabled(false);
        // KDM.getInstance().downloadFile("http://shared.v3.abring.ir/data/applications/com.aseman.nahal/posts/video/2b717d97e79a193af08639da6d73a9ac.mp4", this);
    }

    @Override
    public void onDownloadSuccess(File file) {
        runOnUiThread(() -> {
            textView.setText(String.format(Locale.US, "%s\n%s", textView.getText(), file.getAbsolutePath()));
            button.setEnabled(true);
        });
    }

    @Override
    public void onDownloadFailed(Exception e) {
        runOnUiThread(() -> {
            textView.setText(String.format(Locale.US, "%s\n%s", textView.getText(), e.getMessage()));
            button.setEnabled(true);
        });
    }

    @Override
    public void onDownloadProgress(int totalSize, int downloadedSize, float per) {
        runOnUiThread(() -> {
            textView.setText(String.format(Locale.US, "Downloaded : %dKB / %dKB (%d%%)", downloadedSize, totalSize, (int) per));
            button.setEnabled(true);
        });
    }

    public boolean isStoragePermissionGranted(String permission) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_RC);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

}