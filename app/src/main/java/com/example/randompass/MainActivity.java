package com.example.randompass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textViewRand;
    TextView txtSeekVal;
    Button btnGen;
    static int stringSize;

    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm$%&";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stringSize=1;
        seekBar=(SeekBar) findViewById(R.id.tseekBar);
        textViewRand=(TextView) findViewById(R.id.txtRandpass);
        txtSeekVal=(TextView) findViewById(R.id.txtSeekValue);
        btnGen=(Button) findViewById(R.id.btnGenerate);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtSeekVal.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtSeekVal.getText().toString().isEmpty()) {
                    stringSize= Integer.parseInt(txtSeekVal.getText().toString());
                    textViewRand.setText(getRandomString(stringSize));
                    copyToClipBoard();
                }
                else {
                    Toast.makeText(MainActivity.this, "Specify the password length",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void copyToClipBoard() {
        String pass=textViewRand.getText().toString();
        ClipboardManager clipboardManager=(ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData=ClipData.newPlainText("text",pass);
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(this,"Value ' "+ pass +" 'copied to clipboard",Toast.LENGTH_SHORT).show();
    }

    public static String getRandomString(int stringSize)
    {
        final Random random=new Random();
        final StringBuilder sb = new StringBuilder(stringSize);
        for (int i=0; i<stringSize; i++)
        {
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        }
        return sb.toString();
    }
}
