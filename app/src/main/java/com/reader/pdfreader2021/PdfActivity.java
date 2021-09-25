package com.reader.pdfreader2021;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.util.List;

public class PdfActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener {
    private Integer pageNumber = 0;
    private String pdfFileName;
    private PDFView pdfView;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_pdf);
        this.pdfView = (PDFView) findViewById(R.id.pdfView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        int intExtra = getIntent().getIntExtra("position", 0);
        this.pdfFileName = MainActivity.fileList.get(intExtra).getName();
        this.pdfView.fromFile(MainActivity.fileList.get(intExtra)).defaultPage(this.pageNumber.intValue()).enableSwipe(true).swipeHorizontal(false).onPageChange(this).enableAnnotationRendering(true).onLoad(this).scrollHandle(new DefaultScrollHandle(this)).load();
    }

    @Override // com.github.barteksc.pdfviewer.listener.OnPageChangeListener
    public void onPageChanged(int i, int i2) {
        this.pageNumber = Integer.valueOf(i);
        setTitle(String.format("%s %s / %s", this.pdfFileName, Integer.valueOf(i + 1), Integer.valueOf(i2)));
    }

    @Override // com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
    public void loadComplete(int i) {
        printBookmarksTree(this.pdfView.getTableOfContents(), "-");
    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> list, String str) {
        for (PdfDocument.Bookmark bookmark : list) {
            if (bookmark.hasChildren()) {
                List<PdfDocument.Bookmark> children = bookmark.getChildren();
                printBookmarksTree(children, str + "-");
            }
        }
    }

    @Override // androidx.activity.ComponentActivity
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public boolean onNavigateUp() {
        onBackPressed();
        return super.onNavigateUp();
    }
}
