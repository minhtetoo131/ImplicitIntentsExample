package com.minhtetoo.implicitintentexample;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnShareText,btnShareImg,btnTakePic,btnAddContact,
           btnSendEmail,btnChooseFile,btnOpenMap,btnSendSms,
            btnCallPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShareText =findViewById(R.id.btn_share_text);
        btnShareImg =findViewById(R.id.btn_share_img);
        btnTakePic =findViewById(R.id.btn_take_pic);
        btnAddContact =findViewById(R.id.btn_add_contact);
        btnSendEmail =findViewById(R.id.btn_send_email);
        btnChooseFile =findViewById(R.id.btn_choose_file);
        btnOpenMap =findViewById(R.id.btn_open_map);
        btnSendSms =findViewById(R.id.btn_send_sms);
        btnCallPhone =findViewById(R.id.btn_call_ph);

        btnShareText.setOnClickListener(this);
        btnShareImg.setOnClickListener(this);
        btnTakePic.setOnClickListener(this);
        btnAddContact.setOnClickListener(this);
        btnSendEmail.setOnClickListener(this);
        btnChooseFile.setOnClickListener(this);
        btnOpenMap.setOnClickListener(this);
        btnSendSms.setOnClickListener(this);
        btnCallPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_share_text :
                Intent shareIntentWithText = ShareCompat.IntentBuilder.from(this)
                        .setType("text/plain")
                        .setText(" Sharing with sharecompat")
                        .getIntent();

                startActivity(shareIntentWithText);
                break;

            case R.id.btn_share_img :
//                File imageFile = getAssets(apple.png);
                Uri uriToImage =Uri.parse("android.resource://com.minhtetoo.implicitintentexample/"+R.raw.apple) ;
                Intent shareIntentWithImage = ShareCompat.IntentBuilder.from(this)
                        .setType("image/png")
                        .setStream(uriToImage)
                        .getIntent();
                startActivity(shareIntentWithImage);
                break;

            case R.id.btn_take_pic :
                startActivity(new Intent(this,TakePicActivity.class));
                break;

            case R.id.btn_add_contact :
                                   insertContact("minhtetoo","minminprogramer131@gmail.com");
                break;

            case R.id.btn_send_email :
                                   composeEmail(new String[]{"minminprogramer131@gmail.com"},"Testing email");
                break;

            case R.id.btn_choose_file :
                startActivity(new Intent(this,ChooserActivity.class));
                break;

            case R.id.btn_open_map :
                showMap(Uri.parse("geo:0,0?q=16.77909,96.169941(My Home)"));

                break;

            case R.id.btn_send_sms :
                composeSmsMessage("testing Sms ");
                break;

            case R.id.btn_call_ph :
                dialPhoneNumber("09968058040");
                break;



        }




    }

    public void insertContact(String name, String email) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void composeSmsMessage(String message) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("smsto:")); // This ensures only SMS apps respond
        intent.putExtra("sms_body", message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
