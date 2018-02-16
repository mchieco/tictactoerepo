package ser210.quinnipiac.edu.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    //when play button is clicked, go to InterfaceActivity and carry the name from the edit text
    public void onPlayClick(View view) {
            EditText editText = (EditText) findViewById(R.id.name);
            Intent intent = new Intent(PlayActivity.this, InterfaceActivity.class);
            intent.putExtra("name",editText.getText().toString());
            PlayActivity.this.startActivity(intent);

    }
}
