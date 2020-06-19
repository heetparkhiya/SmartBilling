package com.example.smartbilling.Design;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartbilling.API.ApiClient;
import com.example.smartbilling.API.ApiInterface;
import com.example.smartbilling.Adapter.Adapter_ProductItems;
import com.example.smartbilling.Adapter.Adapter_Spinner_Company;
import com.example.smartbilling.Adapter.Adapter_Spinner_Party;
import com.example.smartbilling.Adapter.Adapter_Spinner_Transport;
import com.example.smartbilling.Bean.Bean_Company;
import com.example.smartbilling.Bean.Bean_Party;
import com.example.smartbilling.Bean.Bean_ProductItems;
import com.example.smartbilling.Bean.Bean_Response_Company;
import com.example.smartbilling.Bean.Bean_Response_Party;
import com.example.smartbilling.Bean.Bean_Response_Transport;
import com.example.smartbilling.Bean.Bean_Transport;
import com.example.smartbilling.DBConstant.DB_CONSTANT;
import com.example.smartbilling.DBHelper.DB_ProductList;
import com.example.smartbilling.R;
import com.example.smartbilling.SessionManager.SessionManager;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import javax.security.auth.login.LoginException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddInvoiceActivity extends AppCompatActivity {
    final Activity activity = this;
    File pdfFile;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;
    ApiInterface apiInterface;
    EditText etInvoiceNumber, etInvoiceDate;
    Spinner spCompany, spParty, spTransport;
    TextView tvQuantityTotal, tvAmountTotal, tvIGSTTotal, tvGrandTotal;
    Calendar CurrentDate;
    int day, month, year;
    String CompanyName = "", CompanyAddress = "", CompanyMobileNo = "", CompanyEmail = "", PartyName = "", PartyAddress = "", PartyMobileNo = "", TransportName = "", TransportMobileNo = "";
    ListView lvProductList;
    ArrayList<Bean_ProductItems> arrayProductItems;
    DB_ProductList db_productList;
    SessionManager manager;
    String TotalQty, TotalAmount, GrandTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_invoice);
        getSupportActionBar().setTitle("Add Invoice");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        init();
        manager = new SessionManager(activity);
        FillSpinner();

        CurrentDate = Calendar.getInstance();
        day = CurrentDate.get(Calendar.DAY_OF_MONTH);
        month = CurrentDate.get(Calendar.MONTH);
        year = CurrentDate.get(Calendar.YEAR);
        etInvoiceDate.setText(day + "/" + (month + 1) + "/" + year);
        etInvoiceDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                        monthofyear = monthofyear + 1;
                        etInvoiceDate.setText(dayOfMonth + "/" + monthofyear + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    void FillSpinner() {
        String UserID = manager.getUserID(activity);
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_Company> callCompany = apiInterface.getAllCompany(UserID);
        callCompany.enqueue(new Callback<Bean_Response_Company>() {
            @Override
            public void onResponse(Call<Bean_Response_Company> call, Response<Bean_Response_Company> response) {
                if (response.body().getResponse() == 1) {
                    final List<Bean_Company> CompanyList = response.body().getData();
                    spCompany.setAdapter(new Adapter_Spinner_Company(CompanyList, activity));
                    spCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            CompanyName = CompanyList.get(position).getCompanyName();
                            CompanyAddress = CompanyList.get(position).getCompanyAddress();
                            CompanyMobileNo = CompanyList.get(position).getCompanyContactNumber();
                            CompanyEmail = CompanyList.get(position).getCompanyEmail();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    progress.dismiss();
                } else {
                    Toast.makeText(activity, "Data not found", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Bean_Response_Company> call, Throwable t) {
                Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });

        Call<Bean_Response_Party> callParty = apiInterface.getAllParty(UserID);
        callParty.enqueue(new Callback<Bean_Response_Party>() {
            @Override
            public void onResponse(Call<Bean_Response_Party> call, Response<Bean_Response_Party> response) {
                if (response.body().getResponse() == 1) {
                    final List<Bean_Party> PartyList = response.body().getData();
                    spParty.setAdapter(new Adapter_Spinner_Party(PartyList, activity));
                    spParty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            PartyName = PartyList.get(position).getPartyName();
                            PartyAddress = PartyList.get(position).getAddress();
                            PartyMobileNo = PartyList.get(position).getMobileNumber();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                    progress.dismiss();
                } else {
                    Toast.makeText(activity, "Data not found", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Bean_Response_Party> call, Throwable t) {
                Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });

        Call<Bean_Response_Transport> callTransport = apiInterface.getAllTransport(UserID);
        callTransport.enqueue(new Callback<Bean_Response_Transport>() {
            @Override
            public void onResponse(Call<Bean_Response_Transport> call, Response<Bean_Response_Transport> response) {
                if (response.body().getResponse() == 1) {
                    final List<Bean_Transport> TransportList = response.body().getData();
                    spTransport.setAdapter(new Adapter_Spinner_Transport(TransportList, activity));
                    spTransport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            TransportName = TransportList.get(position).getTransportName();
                            TransportMobileNo = TransportList.get(position).getTransportMobileNumber();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                    progress.dismiss();
                } else {
                    Toast.makeText(activity, "Data not found", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Bean_Response_Transport> call, Throwable t) {
                Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.add:
                Intent intent = new Intent(activity, AddProductItemsActivity.class);
                startActivity(intent);
                break;

            case R.id.pdf:
                if (etInvoiceNumber.getText().length() > 0) {
                    GeneratePDF();
                } else {
                    etInvoiceNumber.setError("Please Type the Invoice Number");
                }
                break;

            case R.id.cancel:
                Intent invoiceList = new Intent(activity, InvoiceActivity.class);
                startActivity(invoiceList);
                finish();
                break;

            case R.id.clear:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        db_productList.Delete();
                        Toast.makeText(activity, "You can add new bill", Toast.LENGTH_SHORT).show();
                        Intent bill = new Intent(activity, AddInvoiceActivity.class);
                        startActivity(bill);
                        finish();
                    }
                });
                alertDialogBuilder.setNegativeButton("No", null);
                alertDialogBuilder.setMessage("Are you sure clear the bill?");
                alertDialogBuilder.show();
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        db_productList = new DB_ProductList(this);
        arrayProductItems = db_productList.SelectAll();
        if (arrayProductItems.size() > 0) {
            lvProductList.setAdapter(new Adapter_ProductItems(this, arrayProductItems));
        }
        tvAmountTotal.setText(String.valueOf(db_productList.getAmount()));
        tvQuantityTotal.setText(String.valueOf(db_productList.getQuantity()));
        double IGST = Double.parseDouble(tvAmountTotal.getText().toString().trim()) * 0.05;
        tvIGSTTotal.setText(String.valueOf(IGST));
        double GrandTotal = Double.parseDouble(tvAmountTotal.getText().toString().trim()) + IGST;
        tvGrandTotal.setText(String.valueOf(GrandTotal));
    }

    void GeneratePDF() {
        try {
            createPdfWrapper();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createPdfWrapper() throws FileNotFoundException, DocumentException, SQLException {

        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                    }
                                }
                            });
                    return;
                }
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        } else {
            createPdf();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        createPdfWrapper();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(this, "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void createPdf() throws FileNotFoundException, DocumentException, SQLException {

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/SmartBilling");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
        }

        pdfFile = new File(docsFolder.getAbsolutePath(), etInvoiceNumber.getText().toString().trim() + ".pdf");
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document();
        PdfWriter.getInstance(document, output);

        document.open();

        Font Font_20_Bold_Black = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLACK);
        Font Font_25_Bold_Black = new Font(Font.FontFamily.HELVETICA, 25, Font.BOLD, BaseColor.BLACK);
        Font Font_10_Bold_Black = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        Font Font_10_Normal_Black = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        Font Font_12_Bold_Black = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
        Font Font_12_Normal_Black = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);

        PdfPTable Heading_Table = new PdfPTable(1);
        Heading_Table.setWidthPercentage(110);

        PdfPCell HC1 = new PdfPCell(new Phrase("TAX INVOICE", Font_20_Bold_Black));
        HC1.setHorizontalAlignment(Element.ALIGN_CENTER);
        HC1.setBorderWidthTop(1);
        HC1.setBorderWidthBottom(0);
        Heading_Table.addCell(HC1);

        PdfPCell HC2 = new PdfPCell(new Phrase(CompanyName, Font_25_Bold_Black));
        HC2.setHorizontalAlignment(Element.ALIGN_CENTER);
        HC2.setBorderWidthTop(0);
        HC2.setBorderWidthBottom(0);
        Heading_Table.addCell(HC2);

        PdfPCell HC3 = new PdfPCell(new Paragraph(CompanyAddress, Font_12_Normal_Black));
        HC3.setHorizontalAlignment(Element.ALIGN_CENTER);
        HC3.setBorderWidthTop(0);
        HC3.setBorderWidthBottom(0);
        Heading_Table.addCell(HC3);

        PdfPCell HC4 = new PdfPCell(new Paragraph(CompanyMobileNo, Font_12_Normal_Black));
        HC4.setHorizontalAlignment(Element.ALIGN_CENTER);
        HC4.setBorderWidthTop(0);
        HC4.setBorderWidthBottom(0);
        Heading_Table.addCell(HC4);

        PdfPCell HC5 = new PdfPCell(new Paragraph(CompanyEmail, Font_12_Normal_Black));
        HC5.setHorizontalAlignment(Element.ALIGN_CENTER);
        HC5.setBorderWidthTop(0);
        HC5.setBorderWidthBottom(0);
        Heading_Table.addCell(HC5);

        PdfPCell HC6 = new PdfPCell(new Phrase("GST No.:- 1234567890", Font_12_Normal_Black));
        HC6.setHorizontalAlignment(Element.ALIGN_CENTER);
        HC6.setPadding(5);
        Heading_Table.addCell(HC6);
        document.add(Heading_Table);

        PdfPTable Details_Table = new PdfPTable(2);
        Details_Table.setWidthPercentage(110);

        PdfPCell DT = new PdfPCell(new Phrase(
                "Party Name:" + PartyName + "\n\n"
                        + "Address: " + PartyAddress + "\n\n"
                        + "Mobile No.: " + PartyMobileNo + "\n\n"
                        + "PAN No.: -" + "\n\n"
                        + "Agent: -"
                , Font_12_Normal_Black));
        DT.setPadding(5);
        DT.setBorderColor(BaseColor.BLACK);
        Details_Table.addCell(DT);
        document.add(Details_Table);

        DT = new PdfPCell(new Phrase(
                "Date: " + etInvoiceDate.getText().toString() + "\n\n"
                        + "Invoice No.: " + etInvoiceNumber.getText().toString() + "\n\n"
                        + "Road Permit" + "\n\n"
                        + "LR No.: -" + "\n\n"
                        + "Date: " + etInvoiceDate.getText().toString() + "\n\n"
                        + "Transport Name: " + TransportName + "\n\n"
                        + "Transport Mobile No.: " + TransportMobileNo
                , Font_12_Normal_Black));
        DT.setPadding(5);
        DT.setBorderColor(BaseColor.BLACK);
        Details_Table.addCell(DT);

        document.add(Details_Table);

        PdfPTable ItemsHeading_Table = new PdfPTable(20);
        ItemsHeading_Table.setWidthPercentage(110);

        PdfPCell IHT = new PdfPCell(new Phrase("Sr.", Font_10_Bold_Black));
        IHT.setHorizontalAlignment(Element.ALIGN_CENTER);
        IHT.setPadding(5);
        ItemsHeading_Table.addCell(IHT);

        IHT = new PdfPCell(new Phrase("Design No.", Font_10_Bold_Black));
        IHT.setHorizontalAlignment(Element.ALIGN_CENTER);
        IHT.setPadding(5);
        ItemsHeading_Table.addCell(IHT);

        IHT = new PdfPCell(new Phrase("HSN", Font_10_Bold_Black));
        IHT.setHorizontalAlignment(Element.ALIGN_CENTER);
        IHT.setPadding(5);
        ItemsHeading_Table.addCell(IHT);

        IHT = new PdfPCell(new Phrase());
        IHT.setHorizontalAlignment(Element.ALIGN_CENTER);
        IHT.setPadding(5);
        ItemsHeading_Table.addCell(IHT);

        IHT = new PdfPCell(new Phrase("S", Font_10_Bold_Black));
        IHT.setHorizontalAlignment(Element.ALIGN_CENTER);
        IHT.setPadding(5);
        ItemsHeading_Table.addCell(IHT);

        IHT = new PdfPCell(new Phrase("M", Font_10_Bold_Black));
        IHT.setHorizontalAlignment(Element.ALIGN_CENTER);
        IHT.setPadding(5);
        ItemsHeading_Table.addCell(IHT);

        IHT = new PdfPCell(new Phrase("L", Font_10_Bold_Black));
        IHT.setHorizontalAlignment(Element.ALIGN_CENTER);
        IHT.setPadding(5);
        ItemsHeading_Table.addCell(IHT);

        IHT = new PdfPCell(new Phrase("XL", Font_10_Bold_Black));
        IHT.setHorizontalAlignment(Element.ALIGN_CENTER);
        IHT.setPadding(5);
        ItemsHeading_Table.addCell(IHT);

        IHT = new PdfPCell(new Phrase("XXL", Font_10_Bold_Black));
        IHT.setHorizontalAlignment(Element.ALIGN_CENTER);
        IHT.setPadding(5);
        ItemsHeading_Table.addCell(IHT);

        IHT = new PdfPCell(new Phrase("3XL", Font_10_Bold_Black));
        IHT.setHorizontalAlignment(Element.ALIGN_CENTER);
        IHT.setPadding(5);
        ItemsHeading_Table.addCell(IHT);

        IHT = new PdfPCell(new Phrase("2", Font_10_Bold_Black));
        IHT.setHorizontalAlignment(Element.ALIGN_CENTER);
        IHT.setPadding(5);
        ItemsHeading_Table.addCell(IHT);

        IHT = new PdfPCell(new Phrase("4", Font_10_Bold_Black));
        IHT.setHorizontalAlignment(Element.ALIGN_CENTER);
        IHT.setPadding(5);
        ItemsHeading_Table.addCell(IHT);

        IHT = new PdfPCell(new Phrase("6", Font_10_Bold_Black));
        IHT.setHorizontalAlignment(Element.ALIGN_CENTER);
        IHT.setPadding(5);
        ItemsHeading_Table.addCell(IHT);

        IHT = new PdfPCell(new Phrase("8", Font_10_Bold_Black));
        IHT.setHorizontalAlignment(Element.ALIGN_CENTER);
        IHT.setPadding(5);
        ItemsHeading_Table.addCell(IHT);

        IHT = new PdfPCell(new Phrase("10", Font_10_Bold_Black));
        IHT.setHorizontalAlignment(Element.ALIGN_CENTER);
        IHT.setPadding(5);
        ItemsHeading_Table.addCell(IHT);

        IHT = new PdfPCell(new Phrase("12", Font_10_Bold_Black));
        IHT.setHorizontalAlignment(Element.ALIGN_CENTER);
        IHT.setPadding(5);
        ItemsHeading_Table.addCell(IHT);

        IHT = new PdfPCell(new Phrase("14", Font_10_Bold_Black));
        IHT.setHorizontalAlignment(Element.ALIGN_CENTER);
        IHT.setPadding(5);
        ItemsHeading_Table.addCell(IHT);

        IHT = new PdfPCell(new Phrase("16", Font_10_Bold_Black));
        IHT.setHorizontalAlignment(Element.ALIGN_CENTER);
        IHT.setPadding(5);
        ItemsHeading_Table.addCell(IHT);

        IHT = new PdfPCell(new Phrase("Qty", Font_10_Bold_Black));
        IHT.setHorizontalAlignment(Element.ALIGN_CENTER);
        IHT.setPadding(5);
        ItemsHeading_Table.addCell(IHT);

        IHT = new PdfPCell(new Phrase("Amt", Font_10_Bold_Black));
        IHT.setHorizontalAlignment(Element.ALIGN_CENTER);
        IHT.setPadding(5);
        ItemsHeading_Table.addCell(IHT);

        ItemsHeading_Table.setHeaderRows(1);
        Double AllProductsTotalQty = 0.00;
        Double AllProductsTotalAmount = 0.00;
        try {
            SQLiteAssetHelper sql = new SQLiteAssetHelper(this, DB_CONSTANT.DB_NAME, null, DB_CONSTANT.DB_VERSION);
            SQLiteDatabase db = sql.getReadableDatabase();
            String strQuery = "Select * from MST_ProductItems";
            Cursor cur = db.rawQuery(strQuery, null);
            if (cur.moveToFirst()) {
                do {
                    PdfPCell IHT1 = new PdfPCell(new Paragraph("1", Font_10_Normal_Black));
                    IHT1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    IHT1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    IHT1.setPadding(5);
                    ItemsHeading_Table.addCell(IHT1);

                    String DesignNo = (cur.getString(cur.getColumnIndex("DesignNo")));
                    IHT1 = new PdfPCell(new Paragraph(DesignNo, Font_10_Normal_Black));
                    IHT1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    IHT1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    IHT1.setPadding(5);
                    ItemsHeading_Table.addCell(IHT1);

                    IHT1 = new PdfPCell(new Paragraph("6108", Font_10_Normal_Black));
                    IHT1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    IHT1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    IHT1.setPadding(5);
                    ItemsHeading_Table.addCell(IHT1);

                    IHT1 = new PdfPCell(new Paragraph("Qty & Amt", Font_10_Normal_Black));
                    IHT1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    IHT1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    IHT1.setPadding(5);
                    ItemsHeading_Table.addCell(IHT1);

                    String Size = cur.getString(cur.getColumnIndex("Size"));
                    Size = Size.charAt(0) == ',' ? Size.substring(1, Size.length()) : Size;
                    String[] allIdsArray = TextUtils.split(Size, ",");
                    ArrayList<String> idsList = new ArrayList<String>(Arrays.asList(allIdsArray));

                    List<String> Sizes = new ArrayList<>();
                    Sizes.add("S");
                    Sizes.add("M");
                    Sizes.add("L");
                    Sizes.add("XL");
                    Sizes.add("XXL");
                    Sizes.add("3XL");
                    Sizes.add("2");
                    Sizes.add("4");
                    Sizes.add("6");
                    Sizes.add("8");
                    Sizes.add("10");
                    Sizes.add("12");
                    Sizes.add("14");
                    Sizes.add("16");

                    String RateSize = (cur.getString(cur.getColumnIndex("RateSize")).trim());
                    RateSize = RateSize.replaceAll("\\[", "").trim();
                    RateSize = RateSize.replaceAll("]", "").trim();
                    if (RateSize.charAt(0) == ',') {
                        RateSize = RateSize.substring(1, RateSize.length() - 1);
                    }

                    String MRP_Size = (cur.getString(cur.getColumnIndex("MRP_Size")).trim());
                    MRP_Size = MRP_Size.replaceAll("\\[", "").trim();
                    MRP_Size = MRP_Size.replaceAll("]", "").trim();
                    if (MRP_Size.charAt(0) == ',') {
                        MRP_Size = MRP_Size.substring(1, MRP_Size.length() - 1);
                    }

                    String QTY = (cur.getString(cur.getColumnIndex("SizeWiseQty")));
                    QTY = QTY.replaceAll("\\[", "").trim();
                    QTY = QTY.replaceAll("]", "").trim();
                    if (QTY.charAt(0) == ',') {
                        QTY = QTY.substring(1, QTY.length() - 1);
                    }

                    List<String> SizeWiseQTY = Arrays.asList(QTY.split("\\s*,\\s*"));
                    List<String> Rate = Arrays.asList(RateSize.split("\\s*,\\s*"));
                    List<String> MRP = Arrays.asList(MRP_Size.split("\\s*,\\s*"));

                    int j = 0;
                    int TotalQty = 0;
                    int TotalAmount = 0;
                    for (int i = 0; i < Sizes.size(); i++) {
                        if (j < idsList.size() && Sizes.get(i).trim().equals(idsList.get(j).trim())) {
                            TotalQty = TotalQty + (int) Double.parseDouble(SizeWiseQTY.get(j));
                            TotalAmount = TotalAmount + (((int) Double.parseDouble(SizeWiseQTY.get(j))) * ((int) Double.parseDouble(Rate.get(j))));
                            IHT1 = new PdfPCell(new Paragraph(SizeWiseQTY.get(j) + "," + Rate.get(j) + "," + MRP.get(j), Font_10_Normal_Black));
                            IHT1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            IHT1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            IHT1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            IHT1.setPadding(5);
                            ItemsHeading_Table.addCell(IHT1);
                            j++;
                        } else {
                            IHT1 = new PdfPCell(new Paragraph("-", Font_10_Normal_Black));
                            IHT1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            IHT1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            IHT1.setPadding(5);
                            ItemsHeading_Table.addCell(IHT1);
                        }
                    }

                    String Qty = (cur.getString(cur.getColumnIndex("Qty")));
                    IHT1 = new PdfPCell(new Paragraph(String.valueOf(TotalQty), Font_10_Normal_Black));
                    IHT1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    IHT1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    IHT1.setPadding(5);
                    ItemsHeading_Table.addCell(IHT1);

                    AllProductsTotalQty = AllProductsTotalQty + TotalQty;

                    String Amount = (cur.getString(cur.getColumnIndex("Amount")));
                    IHT1 = new PdfPCell(new Paragraph(String.valueOf(TotalAmount), Font_10_Normal_Black));
                    IHT1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    IHT1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    IHT1.setPadding(5);
                    ItemsHeading_Table.addCell(IHT1);

                    AllProductsTotalAmount = AllProductsTotalAmount + TotalAmount;
                } while (cur.moveToNext());
            }
            db.close();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        PdfPCell IHT2 = new PdfPCell(new Paragraph("Total:", Font_10_Bold_Black));
        IHT2.setColspan(18);
        IHT2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        IHT2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        IHT2.setPadding(5);
        ItemsHeading_Table.addCell(IHT2);

        IHT2 = new PdfPCell(new Paragraph(String.valueOf(AllProductsTotalQty), Font_10_Bold_Black));
        IHT2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        IHT2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        IHT2.setPadding(5);
        ItemsHeading_Table.addCell(IHT2);

        IHT2 = new PdfPCell(new Paragraph(String.valueOf(AllProductsTotalAmount), Font_10_Bold_Black));
        IHT2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        IHT2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        IHT2.setPadding(5);
        ItemsHeading_Table.addCell(IHT2);

        PdfPCell IHT3 = new PdfPCell(new Paragraph("TaxableAmount", Font_10_Bold_Black));
        IHT3.setColspan(18);
        IHT3.setHorizontalAlignment(Element.ALIGN_RIGHT);
        IHT3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        IHT3.setBorderWidthBottom(0);
        IHT3.setPadding(5);
        ItemsHeading_Table.addCell(IHT3);

        IHT3 = new PdfPCell(new Paragraph(""));
        IHT3.setColspan(2);
        IHT3.setBorderWidthBottom(0);
        ItemsHeading_Table.addCell(IHT3);

        PdfPCell IHT4 = new PdfPCell(new Paragraph("IGST: 5%", Font_10_Bold_Black));
        IHT4.setColspan(18);
        IHT4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        IHT4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        IHT4.setPadding(5);
        IHT4.setBorderWidthTop(0);
        ItemsHeading_Table.addCell(IHT4);

        IHT4 = new PdfPCell(new Paragraph(tvIGSTTotal.getText().toString(), Font_10_Bold_Black));
        IHT4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        IHT4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        IHT4.setColspan(2);
        IHT4.setPadding(5);
        IHT4.setBorderWidthTop(0);
        ItemsHeading_Table.addCell(IHT4);

        PdfPCell IHT5 = new PdfPCell(new Paragraph("Grand Total", Font_10_Bold_Black));
        IHT5.setColspan(18);
        IHT5.setHorizontalAlignment(Element.ALIGN_RIGHT);
        IHT5.setVerticalAlignment(Element.ALIGN_MIDDLE);
        IHT5.setPadding(5);
        ItemsHeading_Table.addCell(IHT5);

        IHT5 = new PdfPCell(new Paragraph(tvGrandTotal.getText().toString(), Font_10_Bold_Black));
        IHT5.setColspan(2);
        IHT5.setHorizontalAlignment(Element.ALIGN_RIGHT);
        IHT5.setVerticalAlignment(Element.ALIGN_MIDDLE);
        ItemsHeading_Table.addCell(IHT5);

        document.add(ItemsHeading_Table);

        document.close();
        Toast.makeText(this, "PDF File Saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.invoice_menu, menu);
        return true;
    }

    void init() {
        etInvoiceNumber = (EditText) findViewById(R.id.etInvoiceNumber);
        etInvoiceDate = (EditText) findViewById(R.id.etInvoiceDate);
        spCompany = (Spinner) findViewById(R.id.spCompany);
        spParty = (Spinner) findViewById(R.id.spParty);
        spTransport = (Spinner) findViewById(R.id.spTransport);
        tvQuantityTotal = (TextView) findViewById(R.id.tvQuantityTotal);
        tvAmountTotal = (TextView) findViewById(R.id.tvAmountTotal);
        tvIGSTTotal = (TextView) findViewById(R.id.tvIGSTTotal);
        tvGrandTotal = (TextView) findViewById(R.id.tvGrandTotal);
        lvProductList = (ListView) findViewById(R.id.lvProductList);
    }
}