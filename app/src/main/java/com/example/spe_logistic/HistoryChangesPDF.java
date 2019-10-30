package com.example.spe_logistic;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class HistoryChangesPDF {

    private Context context;
    private File pdfFile;
    private Document document;
    private PdfWriter pdfWriter;
    private Paragraph paragraph;
    private Font fTitle = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
    private Font fSubTitle = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private Font fText = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    private Font fHighText = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.RED);

    public HistoryChangesPDF(Context context) {
        this.context = context;
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
        File folder = new File(Environment.getExternalStorageDirectory().toString(), "spe_pdf");

        if (!folder.exists())
            folder.mkdirs();

        pdfFile = new File(folder, "history.pdf");

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
            addChildParagraph(new Paragraph(title, fSubTitle));
            addChildParagraph(new Paragraph("Fecha: " + date, fHighText));
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
                pdfPCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                pdfPTable.addCell(pdfPCell);
            }

            for (int indexRow = 0; indexRow < rows.size(); indexRow++) {
                String[] row = rows.get(indexRow);

                for (indexColumn = 0; indexColumn < row.length; indexColumn++) {
                    pdfPCell = new PdfPCell(new Phrase(row[indexColumn]));
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(40);
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
            Uri uri = Uri.fromFile(pdfFile);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri,"aplication/pdf");
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
}
