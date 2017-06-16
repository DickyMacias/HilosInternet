package mx.netsquare.hilosinternet;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class App extends AppCompatActivity implements View.OnClickListener {

    private Button down;
    private String url = "http://api.androidhive.info/progressdialog/hive.jpg";
    private ImageView imagen = null;
    private ProgressDialog progreso = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app);
        down = (Button)findViewById(R.id.btnDown);
        down.setOnClickListener(this);
        // Imagen
        imagen = (ImageView)findViewById(R.id.imgMostrar);
        imagen.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onClick(View v) {
        new download().execute();

    }

    private class download extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progreso = new ProgressDialog(App.this);
            progreso.setMessage("Descargando Imagen");
            progreso.show();

        }


        @Override
        protected Bitmap doInBackground(String... params) {
            try{
                Bitmap img = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
                return img;

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null){
                imagen.setImageBitmap(bitmap);
                imagen.setVisibility(View.VISIBLE);
            }else{
                imagen.setVisibility(View.VISIBLE);
            }
            progreso.dismiss();
        }
    }
}
