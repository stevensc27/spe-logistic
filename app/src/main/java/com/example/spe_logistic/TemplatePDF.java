package com.example.spe_logistic;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import androidx.core.content.FileProvider;

public class TemplatePDF {

    private String name;
    private Context context;
    private File folder;
    private File pdfFile;
    private File imageFile;
    private Document document;
    private PdfWriter pdfWriter;
    private Paragraph paragraph;
    private Font fTitle = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
    private Font fSubTitle = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
    private Font fText = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
    private Font fHighText = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.RED);

    public TemplatePDF(Context context, String name) {
        this.context = context;
        this.name = name;
    }

    public void openDocument() {
        createFile();
        try {
            document = new Document(PageSize.A4);
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();
        } catch (Exception e) {
            Log.e("openDocument", e.toString());
        }
    }

    private void createFile() {
        folder = new File(Environment.getExternalStorageDirectory().toString(), "spe_pdf");

        if (!folder.exists()) {
            if(!folder.mkdirs())
                Log.e("createFile","Folder not create");
        }

        pdfFile = new File(folder, name+".pdf");

    }

    public void closeDocument() {
        document.close();
    }

    public void addMetaData(String title, String subject, String author) {
        document.addTitle(title);
        document.addSubject(subject);
        document.addAuthor(author);
    }

    public void addTitles(String title, String subTitle, String date) {
        try {
            paragraph = new Paragraph();
            addChildParagraph(new Paragraph(title, fTitle));
            addChildParagraph(new Paragraph(subTitle, fSubTitle));
            addChildParagraph(new Paragraph(date, fHighText));
            paragraph.setSpacingAfter(30);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("addTitles", e.toString());
        }

    }

    private void addChildParagraph(Paragraph childParagraoh) {
        childParagraoh.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(childParagraoh);
    }

    public void addParagraph(String text) {
        try {
            paragraph = new Paragraph(text, fText);
            paragraph.setSpacingAfter(5);
            paragraph.setSpacingBefore(5);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("addParagraph", e.toString());
        }
    }

    public void createTable(String[] header, ArrayList<String[]> rows) {
        try {

            paragraph = new Paragraph();
            paragraph.setFont(fText);
            PdfPTable pdfPTable = new PdfPTable(header.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;

            int indexColumn = 0;
            while (indexColumn < header.length) {
                pdfPCell = new PdfPCell(new Phrase(header[indexColumn++], fSubTitle));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                pdfPTable.addCell(pdfPCell);
            }

            for (int indexRow = 0; indexRow < rows.size(); indexRow++) {
                String[] row = rows.get(indexRow);

                for (indexColumn = 0; indexColumn < row.length; indexColumn++) {

                    pdfPCell = new PdfPCell(new Phrase(row[indexColumn], fText));
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPTable.addCell(pdfPCell);
                }
            }

            paragraph.add(pdfPTable);
            document.add(paragraph);

        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }

    }

    public void appViewPdf(Activity activity){
        if (pdfFile.exists()){
            //Uri uri = Uri.fromFile(pdfFile);
            Uri uri = FileProvider.getUriForFile(context,context.getApplicationContext().getPackageName() + ".provider", pdfFile);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags (Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri,"application/pdf");
            try {
                activity.startActivity(intent);
            }catch (ActivityNotFoundException e){
                activity.startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=com.adobe.reader")));
                Toast.makeText(activity.getApplicationContext(),"No cuentas con app para PDF",Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(activity.getApplicationContext(),"No se encuentra archivo",Toast.LENGTH_LONG).show();
        }
    }

    /*public void addImageFromView(View view){

        Bitmap bitmap;

        view.setDrawingCacheEnabled(true);
        bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        OutputStream fout = null;

        imageFile = new File(Environment.getExternalStorageDirectory().toString(), "spe_pdf/image.jpeg");

        try {
            fout = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fout);
            fout.flush();
            fout.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        Image myImg = null;

        try {

            myImg = Image.getInstance(stream.toByteArray());

            document.add(myImg);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/
}
