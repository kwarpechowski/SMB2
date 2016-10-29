package pl.pjatk.guiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tx = (EditText) findViewById(R.id.editText);
    }

    public void send(View v) {
        Intent intent = new Intent();
        intent.setAction("pl.pjatk.guiapp.CUSTOM_INTENT");
        intent.putExtra("test", tx.getText().toString());
        sendBroadcast(intent);

        tx.setText("");
    }
}
