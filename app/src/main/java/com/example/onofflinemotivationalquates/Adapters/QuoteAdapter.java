package com.example.onofflinemotivationalquates.Adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onofflinemotivationalquates.Modals.QuoteModal;
import com.example.onofflinemotivationalquates.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.quoteHolder> {
  ArrayList<QuoteModal> list;
  Context context;
  Random r = new Random();
   static int i=0;
    int arr[] = {
            R.drawable.one, R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.six,R.drawable.seven,R.drawable.eight,R.drawable.nine,R.drawable.ten
    };
    public QuoteAdapter(ArrayList<QuoteModal> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public quoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quote_row,parent,false);
        return new quoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull quoteHolder holder, int position) {
        int num = r.nextInt(arr.length);
        QuoteModal modal = list.get(position);
        holder.name.setText(modal.getName().trim());
        holder.quote.setText("\n"+modal.getQuote().trim()+"\n");
        holder.backImageRow.setBackgroundResource(arr[num]);
        holder.prev.setOnClickListener(v -> {
            Log.d("I is ", i+"");
          if(i<=arr.length-1 && i>=0){
           holder.backImageRow.setBackgroundResource(arr[i--]);
          }
          else{
              i=0;
          }
        });
        holder.next.setOnClickListener(v -> {
            Log.d("I is ", i+"");
           if(i>=0 && i<=arr.length-1){
           holder.backImageRow.setBackgroundResource(arr[i++]);
          }else{
              i=arr.length-1;
            }
        });
        holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData data = ClipData.newPlainText("copy",holder.quote.getText().toString()+"\n"+ holder.name.getText().toString());
                clipboardManager.setPrimaryClip(data);
                Toast.makeText(v.getContext(), "Copied....", Toast.LENGTH_SHORT).show();
            }
        });
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImg(holder.cnvLaout);
            }
        });
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareTemp(getBitmapView(holder.cnvLaout));
            }
        });

    }

    @Override
    public int getItemCount()
       {
        return list.size();
       }

    class quoteHolder extends RecyclerView.ViewHolder {
        TextView name ,quote,prev,next; ImageView backImageRow,like,share,download,copy;
        LinearLayout cnvLaout;
        public quoteHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameRow);
            backImageRow = itemView.findViewById(R.id.backImageRow);
            quote = itemView.findViewById(R.id.quoteRow);
            prev = itemView.findViewById(R.id.prev);
            next = itemView.findViewById(R.id.next);
            copy = itemView.findViewById(R.id.copyRow);
            share = itemView.findViewById(R.id.shareRow);
            like = itemView.findViewById(R.id.likeRow);
            download = itemView.findViewById(R.id.downRow);
            cnvLaout = itemView.findViewById(R.id.cnvLaout);
        }
    }
    public void downloadImg(View view){
        Bitmap b = getBitmapView(view);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(),
                b, new Date().getTime()+"", null);
        if (path!=null){
            Toast.makeText(context.getApplicationContext(), "Downloaded successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context.getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }
    private  Bitmap getBitmapView(View view){
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable drawable = view.getBackground();
        if(drawable!=null){
            drawable.draw(canvas);
        }else{
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return bitmap;
    }
    public  void shareTemp(Bitmap bitmap){
        try {

            File cachePath = new File(context.getCacheDir(), "images");
            cachePath.mkdirs(); // don't forget to make the directory
            FileOutputStream stream = new FileOutputStream(new File(cachePath, "image.png")); // overwrites this image every time
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File imagePath = new File(context.getCacheDir(), "images");
        File newFile = new File(imagePath, "image.png");
        Uri contentUri = FileProvider.getUriForFile(context, "com.example.onofflinemotivationalquates.fileprovider", newFile);
        if (contentUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
            shareIntent.setDataAndType(contentUri, context.getContentResolver().getType(contentUri));
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(shareIntent);
        }
    }
}
