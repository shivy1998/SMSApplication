package lockdown.org.smsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
    Button btnSend;
    EditText etMobileNo, etMessage;
    protected void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 23)
        {
            if (ContextCompat.checkSelfPermission(MainActivity.this, "android.permission.SEND_SMS") != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{"android.permission.SEND_SMS"},1);
            }
        }
        btnSend = findViewById(R.id.btnSend);
        final SmsManager sms = SmsManager.getDefault();
        etMobileNo = findViewById(R.id.etMobileNo);
        etMessage = findViewById(R.id.etMessage);
        btnSend.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String mobileno = etMobileNo.getText().toString();
                String message = etMessage.getText().toString();
                sms.sendTextMessage(mobileno,null,message,null,null);
            }
        });
    }
}