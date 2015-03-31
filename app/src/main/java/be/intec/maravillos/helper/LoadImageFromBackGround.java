package be.intec.maravillos.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadImageFromBackGround extends AsyncTask<String, Void, Bitmap> {

    ImageView imgView;

    public LoadImageFromBackGround(ImageView imgView){
        this.imgView = imgView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {

        String url = urls[0];
        Bitmap bitmap = null;
        InputStream in = null;
        try{
            in = new URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        }catch (MalformedURLException mef){
            mef.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap s) {
        super.onPostExecute(s);
        imgView.setImageBitmap(s);
    }
}
