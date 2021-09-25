package com.reader.pdfreader2021;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gun0912.tedpicker.Config;
import com.gun0912.tedpicker.ImagePickerActivity;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_GET_IMAGES = 13;
    public static ArrayList<File> fileList = new ArrayList<>();
    private List<String> imagesUri;
    ArrayList<Uri> imagesUris;
    ListView listView;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("PDF's");
        this.imagesUri = new ArrayList();
        this.listView = (ListView) findViewById(R.id.lv_pdf);
        ((FloatingActionButton) findViewById(R.id.createPdf)).setOnClickListener(new View.OnClickListener() {
            /* class com.reader.pdfreader2021.MainActivity.AnonymousClass1 */

            public void onClick(View view) {
                Config config = new Config();
                config.setToolbarTitleRes(R.string.select_image);
                ImagePickerActivity.setConfig(config);
                Intent intent = new Intent(MainActivity.this, ImagePickerActivity.class);
                ArrayList arrayList = new ArrayList(MainActivity.this.imagesUri.size());
                for (String str : MainActivity.this.imagesUri) {
                    arrayList.add(Uri.parse(str));
                }
                intent.putExtra(ImagePickerActivity.EXTRA_IMAGE_URIS, arrayList);
                MainActivity.this.startActivityForResult(intent, 13);
            }
        });
        this.listView.setAdapter((ListAdapter) new PDFAdapter(this, fileList));
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* class com.reader.pdfreader2021.MainActivity.AnonymousClass2 */

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Intent intent = new Intent(MainActivity.this, PdfActivity.class);
                intent.putExtra("position", i);
                MainActivity.this.startActivity(intent);
                }
        });
    }

    public void getfile(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    getfile(listFiles[i]);
                } else if (listFiles[i].getName().endsWith(".pdf")) {
                    boolean z = false;
                    for (int i2 = 0; i2 < fileList.size(); i2++) {
                        if (fileList.get(i2).getName().equals(listFiles[i].getName())) {
                            z = true;
                        }
                    }
                    if (!z) {
                        fileList.add(listFiles[i]);
                    }
                }
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 13 && i2 == -1) {
            this.imagesUri.clear();
            this.imagesUris = intent.getParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);
            for (int i3 = 0; i3 < this.imagesUris.size(); i3++) {
                this.imagesUri.add(this.imagesUris.get(i3).getPath());
            }
            if (this.imagesUri.size() <= 0) {
                Toast.makeText(this, "No Image Selected", 0).show();
            } else {
                new CreatePDFInBackground(this, this.imagesUri).execute(new String[0]);
            }
        }
    }

    public class CreatePDFInBackground extends AsyncTask<String, String, String> {
        private Context context;
        private List<String> imagUri;
        private ProgressDialog progressDialog;

        public CreatePDFInBackground(Context context2, List<String> list) {
            this.imagUri = list;
            this.context = context2;
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            ProgressDialog progressDialog2 = new ProgressDialog(this.context);
            this.progressDialog = progressDialog2;
            progressDialog2.setMessage("PDF Creating...");
            this.progressDialog.setCanceledOnTouchOutside(false);
            this.progressDialog.show();
        }

        /* access modifiers changed from: protected */
        public String doInBackground(String... strArr) {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/ImagesToPDF/");
            if (!file.exists()) {
                file.mkdirs();
            }
            String str = (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/ImagesToPDF/") + "PDF Maker_" + System.currentTimeMillis() + ".pdf";
            Document document = new Document(PageSize.A4, 38.0f, 38.0f, 50.0f, 38.0f);
            try {
                PdfWriter.getInstance(document, new FileOutputStream(str));
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
            Rectangle pageSize = document.getPageSize();
            try {
                document.open();
                for (int i = 0; i < this.imagUri.size(); i++) {
                    Bitmap decodeFile = BitmapFactory.decodeFile(this.imagUri.get(i));
                    decodeFile.compress(Bitmap.CompressFormat.JPEG, 10, new ByteArrayOutputStream());
                    Image instance = Image.getInstance(this.imagUri.get(i));
                    if (((float) decodeFile.getWidth()) <= pageSize.getWidth()) {
                        if (((float) decodeFile.getHeight()) <= pageSize.getHeight()) {
                            instance.scaleToFit((float) decodeFile.getWidth(), (float) decodeFile.getHeight());
                            instance.setAbsolutePosition((pageSize.getWidth() - instance.getScaledWidth()) / 2.0f, (pageSize.getHeight() - instance.getScaledHeight()) / 2.0f);
                            instance.setBorder(15);
                            instance.setBorderWidth(5.0f);
                            document.add(instance);
                            document.newPage();
                        }
                    }
                    instance.scaleToFit(pageSize.getWidth(), pageSize.getHeight());
                    instance.setAbsolutePosition((pageSize.getWidth() - instance.getScaledWidth()) / 2.0f, (pageSize.getHeight() - instance.getScaledHeight()) / 2.0f);
                    instance.setBorder(15);
                    instance.setBorderWidth(5.0f);
                    document.add(instance);
                    document.newPage();
                }
                document.close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            document.close();
            this.imagUri.clear();
            return null;
        }

        /* access modifiers changed from: protected */
        @SuppressLint("WrongConstant")
        public void onPostExecute(String str) {
            super.onPostExecute(str);
            this.progressDialog.dismiss();
            Toast.makeText(MainActivity.this, "PDF Created Successfully", 0).show();
            MainActivity.this.imagesUris.clear();
            MainActivity.this.onResume();
        }
    }

    @Override // androidx.activity.ComponentActivity
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onDestroy() {
        super.onDestroy();
        fileList.clear();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity
    public void onResume() {
        super.onResume();
        fileList.clear();
        getfile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()));
        this.listView.setAdapter((ListAdapter) new PDFAdapter(this, fileList));
    }

    public boolean onNavigateUp() {
        onBackPressed();
        return super.onNavigateUp();
    }
}
