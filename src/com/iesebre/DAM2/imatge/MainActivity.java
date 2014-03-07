package com.iesebre.DAM2.imatge;

import java.io.File;
import java.io.IOException;

import com.iesebre.DAM2.imatge.HttpRequest.HttpRequestException;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btnBoton1 = (Button)findViewById(R.id.boto);
		ImageView img1 = (ImageView)findViewById(R.id.imageView1);
		
		 
		btnBoton1.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View arg0)
		    {
		        String missatge = null;
				Log.d(missatge,"Botón 1 pulsado!");
				DownloadTask baixa = (DownloadTask) new DownloadTask().execute("http://static4.wikia.nocookie.net/__cb20130417191644/disney/images/7/70/Star_wars_yoda.jpg");
		    }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public class DownloadTask extends AsyncTask<String, Long, File> {
		  protected File doInBackground(String... urls) {
		    try {
		      HttpRequest request =  HttpRequest.get(urls[0]);
		      File file = null;
		      if (request.ok()) {
		        try {
					file = File.createTempFile("download", ".tmp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        request.receive(file);
		        publishProgress(file.length());
		      }
		      return file;
		    } catch (HttpRequestException exception) {
		      return null;
		    }
		  }

		  protected void onProgressUpdate(Long... progress) {
		    Log.d("MyApp", "Downloaded bytes: " + progress[0]);
		  }

		  protected void onPostExecute(File file) {
		    if (file != null){
		      Log.d("MyApp", "Downloaded file to: " + file.getAbsolutePath());
		    Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

		    ImageView myImage = (ImageView) findViewById(R.id.imageView1);
		    myImage.setImageBitmap(myBitmap);
		    }

		    else
		      Log.d("MyApp", "Download failed");
		    
		  }
		  
	}{
	

}
}